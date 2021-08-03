package com.janfranco.JWTExample.service.exception;

import com.janfranco.JWTExample.util.response.CustomError;
import org.springframework.http.HttpStatus;

public class NoAuthException extends CustomError {

    public NoAuthException() {
        super(HttpStatus.FORBIDDEN.value(), "You are not allowed to enter here");
    }
}
