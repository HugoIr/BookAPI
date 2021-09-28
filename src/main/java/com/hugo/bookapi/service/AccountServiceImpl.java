package com.hugo.bookapi.service;

import com.hugo.bookapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountServiceImpl implements UserDetailsService, AccountService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String authenticate(String username, String password) throws AuthenticationException {
        String token = "abc";
        System.out.println("here0");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        new ArrayList<>())
        );

        System.out.println("here1");
        token = jwtUtil.generateToken(username);
//        System.out.println("here " + token);
        return token;
    }

    @Override
    public Object loadUserByUsername(String s) throws UsernameNotFoundException {
        return new Object();
    }
}
