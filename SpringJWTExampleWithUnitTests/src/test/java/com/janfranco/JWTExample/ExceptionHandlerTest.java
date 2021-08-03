package com.janfranco.JWTExample;

import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.service.abstracts.UserService;
import com.janfranco.JWTExample.util.FakeDataGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExceptionHandlerTest extends BaseTest {

    @MockBean
    UserService userService;

    @Test
    public void register_fail_internal_server_error() throws Exception {
        User user = FakeDataGenerator.generateFakeUser();
        Mockito.when(userService.register(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("Internal server error"));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user));
        mockMvc.perform(mockRequest)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Internal server error"));
    }
}
