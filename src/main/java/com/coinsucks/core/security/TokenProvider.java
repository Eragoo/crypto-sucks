package com.coinsucks.core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Component
public class TokenProvider {
    private static final String USERNAME_CLAIM = "username";

    private SecurityProps securityProps;

    public String createToken(String username) {
        return JWT.create()
                .withClaim(USERNAME_CLAIM, username)
                .withExpiresAt(Date.from(Instant.now().plus(securityProps.getLifetime())))
                .sign(Algorithm.HMAC512(securityProps.getSignature().getBytes()));
    }

    public Optional<AuthenticatedUser> parseUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityProps.getSignature().getBytes())
                .parseClaimsJws(token)
                .getBody();

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(
                claims.get(USERNAME_CLAIM, String.class)
        );
        return Optional.of(authenticatedUser);
    }
}
