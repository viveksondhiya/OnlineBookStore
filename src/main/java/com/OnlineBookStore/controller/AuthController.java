package com.OnlineBookStore.controller;

import com.OnlineBookStore.Service.AuthService;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.payload.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
        AuthenticationResponse response = authService.authenticateUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(response);
    }
}
