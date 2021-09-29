//package com.hugo.bookapi.controller;
//
//import com.hugo.bookapi.request.AuthRequest;
//import com.hugo.bookapi.response.JwtResponse;
//import com.hugo.bookapi.service.JwtUserDetailsService;
//import com.hugo.bookapi.util.JwtTokenUtil;
////import com.hugo.bookapi.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//public class AuthenticationController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;
//
//
//    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
//
//        try {
//            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//            final UserDetails userDetails = jwtUserDetailsService
//                    .loadUserByUsername(authenticationRequest.getUsername());
//
//            final String token = jwtTokenUtil.generateToken(userDetails);
//
//            return ResponseEntity.ok(new JwtResponse(token));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
//}