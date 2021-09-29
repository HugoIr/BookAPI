package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.exception.BadRequestException;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.repository.BookDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDAO bookDAO;


    @Override
    public Book getBookById(String id) throws Exception {
        try{
            return bookDAO.getBook(id);
        } catch (Exception e) {
        throw new NotFoundException("Book is not available");
    }

    }


    @Override
    public List<Book> getAllBooks() throws NotFoundException {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book postBook(Book book) throws Exception {
        if (book == null) {
            throw new BadRequestException("Please fill with the valid JSON");
        }

        String bookName = book.getBookName();
        String authorName = book.getAuthorName();
        String publicationYear = book.getPublicationYear();

        ArrayList<String> requiredField = new ArrayList<>();

        if (bookName == null) {
            requiredField.add("book name");
        }
        if (authorName == null) {
            requiredField.add("author name");
        }
        if (publicationYear == null) {
            requiredField.add("publication year");
        }
        if (bookName == null || authorName == null || publicationYear == null) {
            throw new BadRequestException("Please fill the required field: " + String.join(", ", requiredField));
        }

        return bookDAO.postBook(book);
    }

}
