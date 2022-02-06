package com.coinsucks.core.user.dto;

import com.coinsucks.core.user.User;
import com.coinsucks.core.user.UserAuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserOutputDto {
    private Long id;
    private String email;
    private UserAuthType userAuthType;
    private String username;

    public UserOutputDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.userAuthType = user.getUserAuthType();
        this.username = user.getUsername();
    }
}
