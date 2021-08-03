package com.janfranco.JWTExample.service;

import com.janfranco.JWTExample.dao.UserRepository;
import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.service.abstracts.UserService;
import com.janfranco.JWTExample.service.exception.InvalidCredentialsException;
import com.janfranco.JWTExample.service.exception.UserAlreadyExistsException;
import com.janfranco.JWTExample.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) throws InvalidCredentialsException {
        User user = userRepository.getByEmail(email);

        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }

    @Override
    public User register(String email, String password, String name) throws UserAlreadyExistsException {
        User user = User.builder().email(email).password(passwordEncoder.encode(password)).name(name).build();
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistsException(email);
        }
        return user;
    }

    @Override
    public User get(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }
}
