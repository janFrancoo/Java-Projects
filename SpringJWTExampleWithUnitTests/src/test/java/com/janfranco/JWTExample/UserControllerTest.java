package com.janfranco.JWTExample;

import com.github.javafaker.Faker;
import com.janfranco.JWTExample.entity.Authority;
import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.service.abstracts.JWTService;
import com.janfranco.JWTExample.service.abstracts.UserService;
import com.janfranco.JWTExample.util.FakeDataGenerator;
import com.janfranco.JWTExample.util.security.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends BaseTest {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @Test
    public void register_success() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.token", matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$")));
    }

    @Test
    public void register_fail_conflict() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        userService.register(user.getEmail(), user.getPassword(), user.getName());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user));
        mockMvc.perform(mockRequest)
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("User already exists: " + user.getEmail()));
    }

    @Test
    public void login_success() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        userService.register(user.getEmail(), user.getPassword(), user.getName());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.token", matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$")));
    }

    @Test
    public void login_fail_wrong_pwd() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        userService.register(user.getEmail(), user.getPassword(), user.getName());
        User userWithWrongPwd = User.builder().id(user.getId()).email(user.getEmail()).password("wrong_pwd")
                .name(user.getName()).authorities(user.getAuthorities()).build();
        userWithWrongPwd.setPassword("wrong_password");
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(userWithWrongPwd));
        mockMvc.perform(mockRequest)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }

    @Test
    public void login_fail_wrong_email() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user));
        mockMvc.perform(mockRequest)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }

    @Test
    public void hello_success() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        User registeredUser = userService.register(user.getEmail(), user.getPassword(), user.getName());
        String token = jwtService.generateToken(registeredUser);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().string(user.getName()));
    }

    @Test
    public void hello_fail_no_token() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest)
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid token"));
    }

    @Test
    public void hello_fail_invalid_token() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        User registeredUser = userService.register(user.getEmail(), user.getPassword(), user.getName());
        String token = jwtService.generateToken(registeredUser);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token + new Faker().number());
        mockMvc.perform(mockRequest)
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid token"));
    }

    @Test
    public void hello_fail_expired_token() throws Exception {
        Object jwtExpiration = ReflectionTestUtils.getField(jwtService, "jwtExpiration");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 0);
        User user = FakeDataGenerator.generateFakeUser();
        User registeredUser = userService.register(user.getEmail(), user.getPassword(), user.getName());
        String token = jwtService.generateToken(registeredUser);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token);
        mockMvc.perform(mockRequest)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Token expired"));
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", jwtExpiration);
    }

    @Test
    public void hello_fail_no_user() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        User registeredUser = userService.register(user.getEmail(), user.getPassword(), user.getName());
        registeredUser.setId(100);
        String token = jwtService.generateToken(registeredUser);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token);
        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    public void admin_success() throws Exception {
        Authority admin = new Authority();
        admin.setName(Role.ADMIN.name());
        User user = FakeDataGenerator.generateFakeUser();
        User registeredUser = userService.register(user.getEmail(), user.getPassword(), user.getName());
        registeredUser.setAuthorities(new ArrayList<>(Collections.singletonList(admin)));
        String token = jwtService.generateToken(registeredUser);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().string(user.getName()));
    }

    @Test
    public void admin_fail_no_auth() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        User registeredUser = userService.register(user.getEmail(), user.getPassword(), user.getName());
        String token = jwtService.generateToken(registeredUser);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/user/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token);
        mockMvc.perform(mockRequest)
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("You are not allowed to enter here"));
    }
}
