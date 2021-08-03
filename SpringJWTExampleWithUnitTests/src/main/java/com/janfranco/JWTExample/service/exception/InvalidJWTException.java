package com.janfranco.JWTExample.service.exception;

import com.janfranco.JWTExample.util.response.CustomError;
import org.springframework.http.HttpStatus;

public class InvalidJWTException extends CustomError {

    public InvalidJWTException() {
        super(HttpStatus.FORBIDDEN.value(), "Invalid token");
    }
}
