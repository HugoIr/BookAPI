package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;

import java.util.List;

public interface BookService {

    Book getBookById(String id) throws Exception;

    Book postBook(Book book) throws Exception;

    List<Book> getAllBooks() throws Exception;
}
