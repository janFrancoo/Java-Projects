package com.janfranco.JWTExample.service.exception;

import com.janfranco.JWTExample.util.response.CustomError;
import org.springframework.http.HttpStatus;

public class JWTExpiredException extends CustomError {

    public JWTExpiredException() {
        super(HttpStatus.UNAUTHORIZED.value(), "Token expired");
    }
}
