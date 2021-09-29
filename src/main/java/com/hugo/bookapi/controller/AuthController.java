package com.hugo.bookapi.controller;

import com.hugo.bookapi.request.AuthRequest;
import com.hugo.bookapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<String> createToken( @RequestBody AuthRequest authRequest) {

        String token = tokenService.generateNewToken();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("AuthorizationToken",
                token);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Successful create the authorization token");
    }
}
