package com.example.javatraining.controller;

import com.example.javatraining.model.Role;
import com.example.javatraining.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //use DTO to get/return the data instead of entity.
    
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleService.saveRole(role);
        return ResponseEntity.ok(savedRole);
    }

    @GetMapping ("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        return roleService.findRoleByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
