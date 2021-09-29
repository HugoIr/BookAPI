package com.hugo.bookapi.repository;

import com.hugo.bookapi.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class BookDAOTest {

    @Autowired
    private BookDAO bookDAO;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setup() {
        book1 = new Book("Book1", "Shane", "2018");
        book2 = new Book("Book1", "Shane", "2018");
        bookDAO.postBook(book1);
        bookDAO.postBook(book2);

    }

    @Test
    public void testDAOGetBook() {
        Book bookResult = bookDAO.getBook(book1.getId());

        assertEquals(book1.getId(), bookResult.getId());
        assertEquals(book1.getBookName(), bookResult.getBookName());
        assertEquals(book1.getAuthorName(), bookResult.getAuthorName());
        assertEquals(book1.getPublicationYear(), bookResult.getPublicationYear());
    }

    @Test
    public void testDAOGetAllBooks() {
        List<Book> bookResult = bookDAO.getAllBooks();
        assertTrue(bookResult.size() > 1);
    }

    @Test
    public void testDAOPostBook() {
        Book book3 = new Book("Book1", "Shane", "2018");
        Book bookResult = bookDAO.postBook(book3);

        assertEquals(book1.getBookName(), bookResult.getBookName());
        assertEquals(book1.getAuthorName(), bookResult.getAuthorName());
        assertEquals(book1.getPublicationYear(), bookResult.getPublicationYear());
    }

    @Test
    public void testDAOPostInvalidBook() {
        Book book3 = null;
        Book bookResult = bookDAO.postBook(book3);

        assertEquals(bookResult, null);
    }
}
