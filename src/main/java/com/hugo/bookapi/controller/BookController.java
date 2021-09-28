package com.hugo.bookapi.controller;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/get/{id}", produces = {"application/json"})
    public Book getBook(@PathVariable String id) {
        return bookDAO.getBook(id);
    }

    @PostMapping("/post")
    public ResponseEntity<Book> postBook(
            @RequestHeader(value = "Accept") String acceptHeader,
            @RequestHeader(value = "AuthorizationName") String authorizationName,
            @RequestHeader(value = "AuthorizationToken") String authorizationToken,
            @RequestBody Book book
    ) {
        ResponseEntity<Book> responseEntity;
        try {
            bookDAO.postBook(book);
        } catch (UnauthorizedException unauthorizedException) {

        }

    }
}
