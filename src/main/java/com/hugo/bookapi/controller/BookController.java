package com.hugo.bookapi.controller;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/get/{id}", produces = {"application/json"})
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        ResponseEntity<Book> response;
        try {
            Book book = bookService.getBookById(id);
            response =  new ResponseEntity<>(book, new HttpHeaders(), HttpStatus.OK);
        } catch (NotFoundException e) {
            response =  new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            response =  new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    @PostMapping("/post")
    public ResponseEntity<Book> postBook(
//            @RequestHeader(value = "Accept") String acceptHeader,
//            @RequestHeader(value = "AuthorizationName") String authorizationName,
//            @RequestHeader(value = "AuthorizationToken") String authorizationToken,
            @RequestHeader HttpHeaders headers,
            @RequestBody Book book
    ) {
        ResponseEntity<Book> response;
        try {
//            System.out.println(headers.getAccept());
//            System.out.println(headers.getDate());
//            System.out.println(headers.toString());
//            System.out.println(headers.toSingleValueMap().get("authorizationname"));
//            System.out.println(headers.toSingleValueMap().get("authorizationtoken"));
             Book newBook = bookService.postBook(book);
             response = new ResponseEntity<>(newBook, null, HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            response = new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
