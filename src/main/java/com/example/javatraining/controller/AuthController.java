package com.example.javatraining.controller;

import com.example.javatraining.dto.AuthRequest;
import com.example.javatraining.dto.AuthResponse;
import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.Role;
import com.example.javatraining.model.User;
import com.example.javatraining.service.RoleService;
import com.example.javatraining.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto) {
        System.out.println("User: " + userDto);
        Optional<Role> role = roleService.findRoleByName(userDto.getRole());
        if (role.isEmpty()) {
            return ResponseEntity.badRequest().body("Role Not Found " + userDto.getRole());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(role.get());
        return ResponseEntity.ok(userService.registerUser(user));
    }

    //it should return a token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        log.info("AuthRequest: " + authRequest);
        final String token = userService.loginUser(authRequest);
        return ResponseEntity.ok(new AuthResponse("Login Successful", token));
    }

}
