package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.repository.BookDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDAO bookDAO;


    @Override
    public Book getBookById(String id) throws Exception {
        Book book = bookDAO.getBook(id);
        if (book == null) {
            throw new NotFoundException("There is no book with id: " + id);
        }
        return book;
    }

    @Override
    public Book postBook(Book book) throws Exception {
        if (book.getBookName() == null) {
            throw new BadRequestException("Please fill the book name");
        }
        return bookDAO.postBook(book);
    }
}
