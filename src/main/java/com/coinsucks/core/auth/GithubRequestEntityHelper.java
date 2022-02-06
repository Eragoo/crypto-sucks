package com.coinsucks.core.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class GithubRequestEntityHelper {
    public static HttpEntity<Map<String, String>> getGithubUserDataHttpEntity(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "token " + accessToken);
        return new HttpEntity<>(Map.of(), httpHeaders);
    }

    public static HttpEntity<Map<String, String>> getAccessTokenHttpEntity(String code, String clientId, String secret) {
        Map<String, String> params = getAccessTokenRequestParams(code, clientId, secret);
        HttpHeaders httpHeaders = getAccessTokenHttpHeaders();

        return new HttpEntity<>(params, httpHeaders);
    }

    private static HttpHeaders getAccessTokenHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private static Map<String, String> getAccessTokenRequestParams(String code, String clientId, String secret) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", secret);
        params.put("code", code);
        return params;
    }
}
