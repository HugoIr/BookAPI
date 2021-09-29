package com.hugo.bookapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.service.BookService;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookService bookService;

    private final String baseUrl = "/book";

    private Book book1;
    private final String nonExsitentId = "-1";
    /**
     * Set up.
     */
    @BeforeEach
    public void setUp() throws Exception {
        book1 = new Book ("Book1", "John Doe", "2020");
        bookService.postBook(book1);
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
    public void testControllerGetBook() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/get/" + book1.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String jsonString = result.getResponse().getContentAsString();

                    String resultId = JsonPath.read(jsonString, "id");
                    String resultBookName = JsonPath.read(jsonString, "bookName");
                    String resultAuthorName = JsonPath.read(jsonString, "authorName");
                    String resultPublicationYear = JsonPath.read(jsonString, "publicationYear");

                    assertEquals(book1.getId(), resultId);
                    assertEquals(book1.getBookName(), resultBookName);
                    assertEquals(book1.getAuthorName(), resultAuthorName);
                    assertEquals(book1.getPublicationYear(), resultPublicationYear);
                });
    }

    @Test
    public void testControllerGetBookWithInvalidAcceptHeader() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/get/" + book1.getId())
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testControllerGetBookWithNonExistentId() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/get/" + nonExsitentId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testControllerPostBook() throws Exception {

        Book book = new Book ("Book1", "John Doe", "2021");


        mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/post")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
