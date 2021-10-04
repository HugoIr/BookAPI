package com.hugo.bookapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.entity.Token;
import com.hugo.bookapi.request.AuthRequest;
import com.hugo.bookapi.service.BookService;
import com.hugo.bookapi.service.TokenService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

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

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateToken() throws Exception {
        AuthRequest request = new AuthRequest("john", "password");

        mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testControllerPostBookWithInvalidToken() throws Exception {

        Book book = new Book ("Book1", "John Doe", "2021");

        mvc.perform(MockMvcRequestBuilders.post("/book/post")
                .content(asJsonString(book))
                .header("authorizationtoken", "-1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(result -> {
                    String jsonString = result.getResponse().getContentAsString();
                    System.out.println(jsonString);
                    assertEquals("Authorization token is not valid", jsonString);
                });
    }

    @Test
    public void testControllerPostBookWithNoToken() throws Exception {

        Book book = new Book ("Book1", "John Doe", "2021");

        mvc.perform(MockMvcRequestBuilders.post("/book/post")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(result -> {
                    String jsonString = result.getResponse().getContentAsString();
                    assertEquals("Please add the authorization token first", jsonString);
                });
    }
}