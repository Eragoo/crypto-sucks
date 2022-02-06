package com.coinsucks.core.auth;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/oauth/github")
@AllArgsConstructor
public class GithubAuthResource {
    private GithubAuthService authenticationService;

    @GetMapping("/oauth-url")
    public String getGithubOauthUrl(@RequestParam String feBaseUri) {
        return authenticationService.getGithubOauthUrl(feBaseUri);
    }

    @GetMapping("/token")
    public String getToken(@RequestParam String code) {
        return authenticationService.getToken(code);
    }
}
