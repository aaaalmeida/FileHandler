package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.JwtModel.JwtRequestModel;
import com.multitech.FileHandler.securityConfig.JwtUtil;
import com.multitech.FileHandler.service.AdminDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdminDetailsService adminDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@ModelAttribute JwtRequestModel model) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(model.username(), model.password()));
        UserDetails userDetails = adminDetailsService.loadUserByUsername(model.username());
        String token = jwtUtil.generateToken(userDetails);

        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false) // true if https
                .path("/")
                .maxAge(3600) // 1 hr
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login efetuado com sucesso");
    }
}
