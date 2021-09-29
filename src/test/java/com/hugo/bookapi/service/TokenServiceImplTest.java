package com.hugo.bookapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    private final String baseUrl = "/auth";

    private String token1;

    /**
     * Set up.
     */
    @BeforeEach
    public void setUp() {
        token1 = tokenService.generateNewToken();
    }

    @Test
    public void testGenerateNewToken() {
        String tokenId = tokenService.generateNewToken();
        assertTrue(tokenId != null);
    }

    @Test
    public void testCheckValidToken() throws Exception {
        assertTrue(tokenService.isTokenValid(token1));
    }

}

