package com.example.javatraining.controller;

import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.Role;
import com.example.javatraining.model.User;
import com.example.javatraining.service.RoleService;
import com.example.javatraining.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



}
