package com.coinsucks.core.user;

import com.coinsucks.core.auth.github.GithubUserData;
import com.coinsucks.core.error.exception.ConflictException;
import com.coinsucks.core.error.exception.NotFoundException;
import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.dto.UserInputDto;
import com.coinsucks.core.user.dto.UserOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(GithubUserData userData) {
        Optional<User> userRepositoryByUsername = userRepository.findByUsername(userData.getLogin());
        if (userRepositoryByUsername.isEmpty()) {
            String pass = passwordEncoder.encode(UUID.randomUUID().toString());
            userRepository.save(
                    new User(null, userData.getLogin(), pass, UserAuthType.GITHUB)
            );
        }
    }

    @Transactional
    public UserOutputDto createUser(UserInputDto inputDto) {
        Optional<User> userRepositoryByUsername = userRepository.findByUsername(inputDto.getUsername());

        if (userRepositoryByUsername.isPresent()) {
            throw new ConflictException("User with provided Username already exist!");
        }

        String pass = passwordEncoder.encode(inputDto.getPassword());
        User user = new User(inputDto.getEmail(), inputDto.getUsername(), pass, UserAuthType.BASIC);
        userRepository.save(user);
        return new UserOutputDto(user);
    }

    @Transactional(readOnly = true)
    public UserOutputDto getCurrent(String username) {
        return userRepository.findByUsername(username)
                .map(UserOutputDto::new)
                .orElseThrow(() -> new NotFoundException("User with provided username not found!"));
    }
}
