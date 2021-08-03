package com.janfranco.JWTExample.service.abstracts;

import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.entity.dto.JWTUserDTO;
import com.janfranco.JWTExample.service.exception.InvalidJWTException;
import com.janfranco.JWTExample.service.exception.JWTExpiredException;

public interface JWTService {

    String generateToken(User user);
    JWTUserDTO verifyToken(String token) throws JWTExpiredException, InvalidJWTException;
}
