package com.hugo.bookapi.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {

    @Test
    public void testUnauthorizedActionMessage() {
        Exception exception = new UnauthorizedException("this action is unauthorized");

        String expectedMessage = "this action is unauthorized";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBadRequestException() {
        Exception exception = new BadRequestException("bad request");

        String expectedMessage = "bad request";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}