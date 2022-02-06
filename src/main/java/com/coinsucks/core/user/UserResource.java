package com.coinsucks.core.user;

import com.coinsucks.core.security.AuthenticatedUser;
import com.coinsucks.core.user.dto.UserInputDto;
import com.coinsucks.core.user.dto.UserOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/current")
    public UserOutputDto getCurrentUser(@AuthenticationPrincipal AuthenticatedUser user) {
        return userService.getCurrent(user.getUsername());
    }

    @PostMapping
    public UserOutputDto create(@RequestBody UserInputDto inputDto) {
        return userService.createUser(inputDto);
    }
}
