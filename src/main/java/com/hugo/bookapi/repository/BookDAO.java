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

    public Book postBook(Book book) {
        if (book != null) {
            return mongoTemplate.save(book);
        }
        return null;
    }





}
