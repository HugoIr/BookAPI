package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    private Book book1;

    private final String nonExsitentId = "-1";

    @BeforeEach
    public void setup() {
        book1 = new Book ("Book1", "John Doe", "2021");
    }

    @Test
    public void testGetBook() throws Exception {
        Book bookResult = bookService.getBookById(book1.getId());

        assertEquals(book1.getId(), bookResult.getId());
        assertEquals(book1.getBookName(), bookResult.getBookName());
        assertEquals(book1.getAuthorName(), bookResult.getAuthorName());
        assertEquals(book1.getPublicationYear(), bookResult.getPublicationYear());
    }

    @Test
    public void testGetNonExistentBook() {

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.getBookById(nonExsitentId);
        });
        assertTrue(exception.getMessage().contains("There is no book with id: " + nonExsitentId));
    }

    @Test
    public void testPostBook() throws Exception {
        Book book = new Book ("Book1", "John Doe", "2020");
        Book bookResult = bookService.postBook(book);

        assertEquals(book.getId(), bookResult.getId());
        assertEquals(book.getBookName(), bookResult.getBookName());
        assertEquals(book.getAuthorName(), bookResult.getAuthorName());
        assertEquals(book.getPublicationYear(), bookResult.getPublicationYear());
    }

    @Test
    public void testPostInvalidBook() {
        Book book = null;

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.postBook(book);
        });
        assertTrue(exception.getMessage().contains("Please fill with the valid JSON"));
    }

    @Test
    public void testPostInvalidBookName() {
        Book book = new Book (null, "John Doe", "2020");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.postBook(book);
        });
        assertTrue(exception.getMessage().contains("Please fill the required field: book name"));
    }

    @Test
    public void testPostInvalidAuthorName() {
        Book book = new Book ("Book1", null, "2020");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.postBook(book);
        });
        assertTrue(exception.getMessage().contains("Please fill the required field: author name"));
    }

    @Test
    public void testPostInvalidPublicationYear() {
        Book book = new Book ("Book1", "John", null);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.postBook(book);
        });
        assertTrue(exception.getMessage().contains("Please fill the required field: publication year"));
    }

    @Test
    public void testPostInvalidAllBookFields() {
        Book book = new Book (null, null, null);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.postBook(book);
        });
        assertTrue(exception.getMessage().contains("Please fill the required field: book name, author name, publication year"));
    }


}

