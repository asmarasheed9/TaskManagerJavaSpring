package com.example.javatraining.service;

import com.example.javatraining.dto.AuthRequest;
import com.example.javatraining.model.User;

public interface UserService {
    User registerUser(User user);
    String loginUser(AuthRequest authRequest);
    User findUserByEmail(String email);
}
