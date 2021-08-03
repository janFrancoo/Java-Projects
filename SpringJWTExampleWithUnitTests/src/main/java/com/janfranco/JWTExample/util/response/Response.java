package com.janfranco.JWTExample.util.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Response<T> {

    @JsonIgnore
    private final int status;

    private final boolean success;
    private final String message;
    private final T data;

    public Response(ResponseBuilder<T> builder) {
        this.status = builder.status;
        this.success = builder.success;
        this.message = builder.message;
        this.data = builder.data;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static class ResponseBuilder<T> {

        private int status;
        private boolean success;
        private String message;
        private T data;

        public ResponseBuilder<T> status(int status) {
            this.status = status;
            this.success = status >= 200 && status < 300;
            return this;
        }

        public ResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Response<T> build() {
            return new Response<>(this);
        }
    }
}
