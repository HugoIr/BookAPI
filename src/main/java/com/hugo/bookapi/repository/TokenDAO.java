package com.hugo.bookapi.repository;

import com.hugo.bookapi.entity.Book;
import com.hugo.bookapi.entity.Token;
import com.hugo.bookapi.exception.UnauthorizedException;
import javassist.NotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    public Token getToken(String id) throws Exception{
        if (id == null) {
            throw new UnauthorizedException("Please add the authorization token first");
        } else if (mongoTemplate.findById(id, Token.class) == null) {
            throw new UnauthorizedException("Authorization token is not valid");
        }
        return mongoTemplate.findById(id, Token.class);
    }

    public Token saveToken(Token token) {
        if (token != null) {
            return mongoTemplate.save(token);
        }
        return null;
    }


}