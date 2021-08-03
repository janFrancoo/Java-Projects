package com.janfranco.JWTExample.middleware;

import com.janfranco.JWTExample.entity.dto.JWTUserDTO;
import com.janfranco.JWTExample.service.abstracts.JWTService;
import com.janfranco.JWTExample.middleware.annotations.Authorized;
import com.janfranco.JWTExample.service.exception.InvalidJWTException;
import com.janfranco.JWTExample.service.exception.JWTExpiredException;
import com.janfranco.JWTExample.service.exception.NoAuthException;
import com.janfranco.JWTExample.util.security.Role;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class AuthMiddleware {

    @Autowired
    private JWTService jwtService;

    @Before("@annotation(authorized)")
    public void auth(Authorized authorized) throws InvalidJWTException, JWTExpiredException, NoAuthException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null || bearerToken.isEmpty() || !bearerToken.startsWith("Bearer ")) {
            throw new InvalidJWTException();
        }

        String token = bearerToken.replace("Bearer ", "");
        JWTUserDTO jwtUserDTO = jwtService.verifyToken(token);

        List<String> roles = jwtUserDTO.getRoles();
        Role[] requiredRoles = authorized.roles();

        for (Role requiredRole : requiredRoles) {
            if (roles.stream().anyMatch(r -> r.equals(requiredRole.name()))) {
                request.setAttribute("user", jwtUserDTO);
                return;
            }
        }

        if (requiredRoles.length == 0) {
            request.setAttribute("user", jwtUserDTO);
            return;
        }

        throw new NoAuthException();
    }
}
