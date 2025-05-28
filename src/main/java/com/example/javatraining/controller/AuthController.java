package com.example.javatraining.controller;

import com.example.javatraining.dto.ApiResponse;
import com.example.javatraining.dto.AuthRequest;
import com.example.javatraining.dto.AuthResponse;
import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.User;
import com.example.javatraining.service.JwtService;
import com.example.javatraining.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Use Registered", userService.registerUser(userDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                authRequest.getPassword()));
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new ApiResponse(true, "Welcome, loggedIn User", new AuthResponse(token)));
    }

}
