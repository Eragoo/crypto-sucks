package com.coinsucks.core.user;

import com.coinsucks.core.auth.GithubUserData;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void createUser(GithubUserData userData) {
        Optional<User> userRepositoryByUsername = userRepository.findByUsername(userData.getLogin());
        if (userRepositoryByUsername.isEmpty()) {
            String pass = passwordEncoder.encode(UUID.randomUUID().toString());
            userRepository.save(
                    new User(null, userData.getLogin(), pass, UserAuthType.GITHUB)
            );
        }
    }
}
