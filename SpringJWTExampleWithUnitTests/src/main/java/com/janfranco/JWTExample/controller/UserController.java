package com.janfranco.JWTExample.controller;

import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.entity.dto.JWTUserDTO;
import com.janfranco.JWTExample.entity.dto.TokenDTO;
import com.janfranco.JWTExample.entity.dto.UserLoginDTO;
import com.janfranco.JWTExample.entity.dto.UserRegisterDTO;
import com.janfranco.JWTExample.service.abstracts.JWTService;
import com.janfranco.JWTExample.service.abstracts.UserService;
import com.janfranco.JWTExample.middleware.annotations.Authorized;
import com.janfranco.JWTExample.service.exception.InvalidCredentialsException;
import com.janfranco.JWTExample.service.exception.UserAlreadyExistsException;
import com.janfranco.JWTExample.service.exception.UserNotFoundException;
import com.janfranco.JWTExample.util.response.Response;
import com.janfranco.JWTExample.util.security.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(value = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public Response<TokenDTO> login(@RequestBody UserLoginDTO userLoginDTO) throws InvalidCredentialsException {
        User user = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        TokenDTO tokenDTO = new TokenDTO(jwtService.generateToken(user));
        return new Response.ResponseBuilder<TokenDTO>().status(HttpStatus.OK.value()).data(tokenDTO).build();
    }

    @PostMapping("/register")
    public Response<TokenDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) throws UserAlreadyExistsException {
        User user = userService.register(userRegisterDTO.getEmail(), userRegisterDTO.getPassword(), userRegisterDTO.getName());
        TokenDTO tokenDTO = new TokenDTO(jwtService.generateToken(user));
        return new Response.ResponseBuilder<TokenDTO>().status(HttpStatus.OK.value()).data(tokenDTO).build();
    }

    @GetMapping("/hello")
    @Authorized
    @ApiOperation(value = "JWT authorization test")
    public String hello(HttpServletRequest request) throws UserNotFoundException {
        JWTUserDTO jwtUserDTO = (JWTUserDTO) request.getAttribute("user");
        User user = userService.get(jwtUserDTO.getId());
        return user.getName();
    }

    @GetMapping("/admin")
    @Authorized(roles = {Role.ADMIN})
    @ApiOperation(value = "JWT authorization for admin test")
    public String admin(HttpServletRequest request) throws UserNotFoundException {
        JWTUserDTO jwtUserDTO = (JWTUserDTO) request.getAttribute("user");
        User user = userService.get(jwtUserDTO.getId());
        return user.getName();
    }
}
