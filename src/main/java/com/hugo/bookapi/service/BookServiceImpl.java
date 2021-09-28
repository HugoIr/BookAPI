package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDAO bookDAO;

    public Book postBook(Book book) throws Exception {
        if (book.getBookName() == null) {
            throw new BadRequestException("Please fill the book name");
        }
    }
}
