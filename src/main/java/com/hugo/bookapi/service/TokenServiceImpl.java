package com.hugo.bookapi.service;

import com.hugo.bookapi.entity.Token;
import com.hugo.bookapi.exception.UnauthorizedException;
import com.hugo.bookapi.repository.TokenDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService{

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Autowired
    TokenDAO tokenDAO;

    @Override
    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);
        saveToken(token);
        return token;
    }

    @Override
    public boolean isTokenValid(String token) throws Exception {
        return tokenDAO.getToken(token) != null;
    }

    private void saveToken(String token) {
        Token newToken = new Token(token);
        tokenDAO.saveToken(newToken);
    }
}
