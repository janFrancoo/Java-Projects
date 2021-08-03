package com.janfranco.JWTExample.service.abstracts;

import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.service.exception.InvalidCredentialsException;
import com.janfranco.JWTExample.service.exception.UserAlreadyExistsException;
import com.janfranco.JWTExample.service.exception.UserNotFoundException;

public interface UserService {

    User login(String email, String password) throws InvalidCredentialsException;
    User register(String email, String password, String name) throws UserAlreadyExistsException;
    User get(int id) throws UserNotFoundException;
}
