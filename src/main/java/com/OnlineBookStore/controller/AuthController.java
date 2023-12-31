package com.OnlineBookStore.controller;

import com.OnlineBookStore.Service.AuthService;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.payload.AuthenticationResponse;
import com.OnlineBookStore.payload.ChangePasswordResponse;
import com.OnlineBookStore.payload.ForgotPasswordRequest;
import com.OnlineBookStore.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
        AuthenticationResponse response = authService.authenticateUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(response);
    }

@PutMapping("/change-password")
public ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ForgotPasswordRequest request) {
    String email = request.getEmail();
    String newPassword = request.getNewPassword();

    User user = userRepo.findByEmail(email);

    if (user == null) {
        return new ResponseEntity<>(new ChangePasswordResponse("error", "User not found"), HttpStatus.NOT_FOUND);
    }

    // Print the received values for debugging
    System.out.println("Email: " + email);
    System.out.println("New Password: " + newPassword);

    // Update the password
    user.setPassword(newPassword);
    userRepo.save(user);

    ChangePasswordResponse response = new ChangePasswordResponse("success", "Password changed successfully");
    return new ResponseEntity<>(response, HttpStatus.OK);
}
}
