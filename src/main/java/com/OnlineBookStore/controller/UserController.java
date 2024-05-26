package com.OnlineBookStore.controller;

import com.OnlineBookStore.Service.UserService;
import com.OnlineBookStore.payload.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

//    @PostMapping("/createUser")
//    public ResponseEntity<String>createUser(@Valid @RequestBody UserDto userDto){
//        String res=this.userService.createUser(userDto);
//        return  new ResponseEntity<>(res, HttpStatus.CREATED);
//    }
@PostMapping("/createUser")
public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
    Map<String, Object> response = new HashMap<>();

    if (result.hasErrors()) {
        List<String> errors = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        response.put("success", false);
        response.put("message", "Please put valid data");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    String message = this.userService.createUser(userDto);

    if (message.startsWith("User created successfully")) {
        response.put("success", true);
        response.put("message", message);
        response.put("userId", extractUserId(message)); // Extract user ID from the message
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } else {
        response.put("success", false);
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

    // Helper method to extract user ID from the message
    private Long extractUserId(String message) {
        // Parse the user ID from the message (example: "User created successfully with ID: 123")
        String[] parts = message.split(":");
        return Long.parseLong(parts[1].trim());
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Long uId){
        UserDto updatedUser=this.userService.updateUser(userDto,uId);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity("User Deleted Successfully",HttpStatus.OK);
    }

}
