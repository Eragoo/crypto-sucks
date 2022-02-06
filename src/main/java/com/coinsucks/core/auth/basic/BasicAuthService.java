package com.coinsucks.core.auth.basic;

import com.coinsucks.core.error.exception.NotFoundException;
import com.coinsucks.core.security.TokenProvider;
import com.coinsucks.core.user.User;
import com.coinsucks.core.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class BasicAuthService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenProvider tokenProvider;

    public String getToken(UserAuthInputDto command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || isPasswordNotMatches(user.get().getPassword(), command.getPassword())) {
            throw new NotFoundException("User with provided username and password not found");
        }

        return tokenProvider.createToken(user.get().getUsername());
    }

    private boolean isPasswordNotMatches(String expectedEncodedPassword, String receivedRawPassword) {
        assert expectedEncodedPassword != null && receivedRawPassword != null;
        return !bCryptPasswordEncoder.matches(receivedRawPassword, expectedEncodedPassword);
    }
}
