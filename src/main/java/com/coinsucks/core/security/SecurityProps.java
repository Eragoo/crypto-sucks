package com.coinsucks.core.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecurityProps {
    @Value("${application.security.signature}")
    private String signature;
    @Value("${application.security.lifetime}")
    private Duration lifetime;
}
