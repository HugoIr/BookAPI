package com.hugo.bookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(value = "token")
@Setter
@Getter
@AllArgsConstructor
public class Token {

    @MongoId
    private String token;
}
