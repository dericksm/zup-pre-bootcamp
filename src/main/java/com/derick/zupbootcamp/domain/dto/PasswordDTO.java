package com.derick.zupbootcamp.domain.dto;

import com.derick.zupbootcamp.domain.entities.ConfirmationToken;

import javax.validation.constraints.Size;

public class PasswordDTO {

    @Size(min = 8, message = "Password should have eight characters")
    @Size(max = 8, message = "Password should have eight characters")
    private String password;

    private String token;

    public PasswordDTO() {
    }

    public PasswordDTO(String password, String token) {
        this.password = password;
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
