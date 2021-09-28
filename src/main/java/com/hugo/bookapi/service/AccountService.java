package com.hugo.bookapi.service;


import org.springframework.security.core.AuthenticationException;

public interface AccountService {
    String authenticate(String username, String password) throws AuthenticationException;
}
