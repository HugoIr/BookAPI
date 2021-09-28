package com.hugo.bookapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "book")
@Getter
@Setter
public class Book {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String bookName;

    private String authorName;

    private String publicationYear;
}
