package com.janfranco.JWTExample.service.exception;

import com.janfranco.JWTExample.util.response.CustomError;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends CustomError {

    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials");
    }
}
