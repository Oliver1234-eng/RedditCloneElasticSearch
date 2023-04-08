package com.ftn.redditcloneprojekat.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private String secretKey;

    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
