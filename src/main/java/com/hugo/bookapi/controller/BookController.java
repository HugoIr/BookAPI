package com.hugo.bookapi.controller;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.service.BookService;
import com.hugo.bookapi.service.TokenService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private TokenService tokenService;

    @GetMapping(path = "/get/{id}", produces = {"application/json"})
    public ResponseEntity<Object> getBook(@PathVariable String id,
                                          @RequestHeader HttpHeaders headers
    ) {
        ResponseEntity<Object> response;
        try {
            Map<String, String> header = headers.toSingleValueMap();

            tokenService.isTokenValid(header.get("authorizationtoken"));
            Book book = bookService.getBookById(id);
            response =  new ResponseEntity<>(book, new HttpHeaders(), HttpStatus.OK);
        } catch (UnauthorizedException e) {
            response = new ResponseEntity<>(e.getMessage(), null, HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            response =  new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            response =  new ResponseEntity<>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
        return response;

    }

    @GetMapping(path = "/get/all", produces = {"application/json"})
    public ResponseEntity<Object> getBook(@RequestHeader HttpHeaders headers) {

        ResponseEntity<Object> response;
        try {
            Map<String, String> header = headers.toSingleValueMap();

            tokenService.isTokenValid(header.get("authorizationtoken"));
            List<Book> books = bookService.getAllBooks();
            response =  new ResponseEntity<>(books, new HttpHeaders(), HttpStatus.OK);
        } catch (UnauthorizedException e) {
            response = new ResponseEntity<>(e.getMessage(), null, HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            response =  new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            response =  new ResponseEntity<>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
        return response;

    }

    @PostMapping("/post")
    public ResponseEntity<Object> postBook(
            @RequestHeader HttpHeaders headers,
            @RequestBody Book book
    ) {
        ResponseEntity<Object> response;
        try {
            Map<String, String> header = headers.toSingleValueMap();

            tokenService.isTokenValid(header.get("authorizationtoken"));
             Book newBook = bookService.postBook(book);
             response = new ResponseEntity<>(newBook, null, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            response = new ResponseEntity<>(e.getMessage(), null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
