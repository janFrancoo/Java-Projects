package com.janfranco.JWTExample.middleware;

import com.janfranco.JWTExample.util.response.CustomError;
import com.janfranco.JWTExample.util.response.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerMiddleware extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomError.class)
    @ResponseBody
    protected ResponseEntity<Response<Void>> handleException(CustomError error) {
        return buildResponseEntity(error.getStatus(), error.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected ResponseEntity<Response<Void>> handleException(Exception exception) {
        if (exception.getCause() instanceof CustomError) {
            return buildResponseEntity(((CustomError) exception.getCause()).getStatus(), exception.getCause().getMessage());
        }
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage());
    }

    private ResponseEntity<Response<Void>> buildResponseEntity(int status, String message) {
        Response<Void> response = new Response.ResponseBuilder<Void>()
                .status(status)
                .message(message)
                .build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
