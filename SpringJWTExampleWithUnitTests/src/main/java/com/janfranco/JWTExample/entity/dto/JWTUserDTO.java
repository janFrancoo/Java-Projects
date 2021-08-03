package com.janfranco.JWTExample.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JWTUserDTO {

    private int id;
    private List<String> roles;

    public JWTUserDTO(int id, List<String> roles) {
        this.id = id;
        this.roles = roles;
    }
}
