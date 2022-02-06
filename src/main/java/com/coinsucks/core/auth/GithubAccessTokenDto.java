package com.coinsucks.core.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GithubAccessTokenDto {
    private String access_token;
    private String scope;
    private String token_type;
}
