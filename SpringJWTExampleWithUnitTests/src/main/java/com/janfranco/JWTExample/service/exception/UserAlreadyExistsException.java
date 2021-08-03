package com.janfranco.JWTExample.service.exception;

import com.janfranco.JWTExample.util.response.CustomError;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends CustomError {

    public UserAlreadyExistsException(String email) {
        super(HttpStatus.CONFLICT.value(), "User already exists: " + email);
    }
}
