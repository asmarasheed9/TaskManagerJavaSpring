package com.example.javatraining.service;

import com.example.javatraining.dto.AuthRequest;
import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.User;
import java.util.List;

public interface UserService {
    User registerUser(UserDto user);
    String loginUser(AuthRequest authRequest);
    User findUserByEmail(String email);
    User findUserByUuidId(String Id);
    List<User> getAllUsers();
}
