package com.janfranco.JWTExample.entity.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {

    private String email;
    private String password;
    private String name;

    public UserRegisterDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
