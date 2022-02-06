package com.coinsucks.core.security;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AuthenticatedUser extends User {
    private String username;

    public AuthenticatedUser(String username) {
        super(username, "", Collections.emptyList());
        this.username = username;
    }
}
