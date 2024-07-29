package com.example.itau.controller;

import com.example.itau.controllers.JwtController;
import com.example.itau.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

@WebMvcTest(JwtController.class)
class JwtControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @Test
    void testValidateJwtValidToken() throws Exception {
        String token = "validToken";

        // Mockando o comportamento do JwtService
        when(jwtService.validateJwt(token)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/validate")
                        .param("token", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(true));

        // Verifica se o método validateJwt foi chamado com o token correto
        verify(jwtService).validateJwt(token);
    }

    @Test
    void testValidateJwtInvalidToken() throws Exception {
        String token = "invalidToken";

        // Mockando o comportamento do JwtService
        when(jwtService.validateJwt(token)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/validate")
                        .param("token", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(false));

        // Verifica se o método validateJwt foi chamado com o token correto
        verify(jwtService).validateJwt(token);
    }
}
