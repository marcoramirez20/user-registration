package com.example.users.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.validation")
public class ValidationProperties {
    private String emailRegex;
    private String passwordRegex;

    public String getEmailRegex() { return emailRegex; }
    public void setEmailRegex(String emailRegex) { this.emailRegex = emailRegex; }
    public String getPasswordRegex() { return passwordRegex; }
    public void setPasswordRegex(String passwordRegex) { this.passwordRegex = passwordRegex; }
}
