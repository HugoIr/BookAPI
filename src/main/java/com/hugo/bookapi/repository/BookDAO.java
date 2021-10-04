package com.hugo.bookapi.repository;

import com.hugo.bookapi.entity.Book;
import javassist.NotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    public Book getBook(String id) {
        return mongoTemplate.findById(id, Book.class);
    }

    public List<Book> getAllBooks() {
        return mongoTemplate.findAll(Book.class);
    }

    public Book postBook(Book book) {
        if (book != null) {
            return mongoTemplate.save(book);
        }
        return null;
    }


}
