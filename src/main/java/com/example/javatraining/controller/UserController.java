package com.example.javatraining.controller;
import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.User;
import com.example.javatraining.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        User currentUser =  (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.findUserByEmail(currentUser.getEmail()));
    }

}
