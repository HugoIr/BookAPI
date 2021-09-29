package com.hugo.bookapi.repository;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.entity.Token;
import com.hugo.bookapi.exception.UnauthorizedException;
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
public class TokenDAOTest {

    @Autowired
    private TokenDAO tokenDAO;

    private Token token1;
    private String token2;
    private Book book2;

    @BeforeEach
    public void setup() {
        token1 = new Token("123456789");
        tokenDAO.saveToken(token1);
//        bookDAO.postBook(book1);
//        bookDAO.postBook(book2);

    }

    @Test
    public void testGetToken() throws Exception {
        Token tokenResult = tokenDAO.getToken("123456789");
        assertEquals(token1.getToken(), tokenResult.getToken());
    }

    @Test
    public void testGetTokenWithNullId() throws Exception {

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            tokenDAO.getToken(null);
        });

        String expectedMessage = "Please add the authorization token first";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetNonExistentToken() throws Exception {

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            tokenDAO.getToken("-1");
        });

        String expectedMessage = "Authorization token is not valid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }




}
