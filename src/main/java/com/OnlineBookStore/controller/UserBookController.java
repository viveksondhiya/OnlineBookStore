package com.OnlineBookStore.controller;


import com.OnlineBookStore.Service.AuthService;
import com.OnlineBookStore.Service.UserBooks;
import com.OnlineBookStore.Service.UserService;
import com.OnlineBookStore.entity.User;
import com.OnlineBookStore.payload.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userBook")
public class UserBookController {
    @Autowired
    private UserBooks userBooks;

    @PostMapping("/{userId}/assign-book/{bookId}")
    public ResponseEntity<String> assignBookToUser(
            @PathVariable long userId,
            @PathVariable long bookId
    ) {
        try {
            userBooks.assignBookToUser(userId, bookId);
            return new ResponseEntity<>("Book assigned to user successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
