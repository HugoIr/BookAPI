package com.hugo.bookapi.repository;

import com.hugo.bookapi.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    public Book getBook(String id) {
        return mongoTemplate.findById(id, Book.class);
    }

    public String postBook(Book book) {
        if (book != null) {
            mongoTemplate.save(book);
            return "Successful save the book with id " + book.getId();
        }
        return "Failed to save the book";
    }





}
