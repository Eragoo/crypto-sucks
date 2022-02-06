package com.coinsucks.core.auth.github;

import com.coinsucks.core.security.TokenProvider;
import com.coinsucks.core.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

import java.util.Map;

import static com.coinsucks.core.auth.github.GithubRequestEntityHelper.getAccessTokenHttpEntity;
import static com.coinsucks.core.auth.github.GithubRequestEntityHelper.getGithubUserDataHttpEntity;

@Service
@AllArgsConstructor
public class GithubAuthService {
    private final RestOperations restOperations;
    private final TokenProvider tokenProvider;
    private final GithubSecurityProps securityData;
    private final UserService userService;

    private static final String baseUrl = "https://github.com/login/oauth/authorize?";
    private static final String scope = "user:email";
    private static final String responseType = "code";
    private static final String accessTokenResourceUrl = "https://github.com/login/oauth/access_token";
    private static final String userGettingUrl = "https://api.github.com/user";

    public String getGithubOauthUrl(String feBaseUri) {
        return baseUrl
                + "client_id=" + securityData.getClientId()
                + "&scope=" + scope
                + "&redirect_uri=" + "http%3A%2F%2F" + feBaseUri + "%3A8080%2Flogin%2Foauth2%2Fcode%2Fgithub"
                + "&response_type=" + responseType;
    }

    public String getToken(String accessCode) {
        String accessToken = getAccessToken(accessCode);
        GithubUserData githubUserData = getGithubUserData(accessToken);
        userService.createUser(githubUserData);
        return createToken(githubUserData.getName());
    }

    private String getAccessToken(String code) {
        return getGithubAccessTokenDto(code).getAccess_token();
    }

    private GithubAccessTokenDto getGithubAccessTokenDto(String code) {
        HttpEntity<Map<String, String>> requestEntity = getAccessTokenHttpEntity(code,
                securityData.getClientId(),
                securityData.getSecret());

        ResponseEntity<GithubAccessTokenDto> responseEntity = restOperations.exchange(accessTokenResourceUrl,
                HttpMethod.POST,
                requestEntity,
                GithubAccessTokenDto.class);

        return responseEntity.getBody();
    }

    private GithubUserData getGithubUserData(String accessToken) {
        HttpEntity<Map<String, String>> httpEntity = getGithubUserDataHttpEntity(accessToken);
        ResponseEntity<GithubUserData> responseEntity = restOperations.exchange(userGettingUrl,
                HttpMethod.GET,
                httpEntity,
                GithubUserData.class);

        return responseEntity.getBody();
    }

    private String createToken(String username) {
        return tokenProvider.createToken(username);
    }
}
