package com.hugo.bookapi.service;

import javassist.NotFoundException;

public interface TokenService {
    String generateNewToken();
    boolean isTokenValid(String token) throws Exception;
}
