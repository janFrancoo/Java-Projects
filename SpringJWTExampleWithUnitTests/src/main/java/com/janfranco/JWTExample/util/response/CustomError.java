package com.janfranco.JWTExample.util.response;

public abstract class CustomError extends Exception {

    private final int status;
    private final String message;

    public CustomError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
