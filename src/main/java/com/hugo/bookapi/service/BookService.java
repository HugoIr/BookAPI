package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;

public interface BookService {

    public Book getBookById(String id) throws Exception;

    public Book postBook(Book book) throws Exception;
}
