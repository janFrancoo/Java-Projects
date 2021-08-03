package com.janfranco.JWTExample.service.exception;

import com.janfranco.JWTExample.util.response.CustomError;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomError {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "User not found");
    }
}
