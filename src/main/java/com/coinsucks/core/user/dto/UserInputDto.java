package com.coinsucks.core.user.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInputDto {
    private final String email;
    private final String username;
    private final String password;
}
