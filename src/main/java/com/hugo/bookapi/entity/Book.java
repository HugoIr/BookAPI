package com.hugo.bookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "book")
@Getter
@Setter
public class Book {

    @MongoId()
    private String id;

    private String bookName;

    private String authorName;

    private String publicationYear;

    public Book(String bookName, String authorName, String publicationYear) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.publicationYear = publicationYear;
    }
}
