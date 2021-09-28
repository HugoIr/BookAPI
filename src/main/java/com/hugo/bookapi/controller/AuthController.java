package com.hugo.bookapi.controller;

import com.hugo.bookapi.request.AuthRequest;
import com.hugo.bookapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/token", produces = {"application/json"} )
    @ResponseBody
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
        ResponseEntity<String> response;
        try {
            String token = accountService.authenticate(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );
            System.out.println("token " + token);
            response = new ResponseEntity<String>(token, null, HttpStatus.OK);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            response = new ResponseEntity<String>(null, null, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
