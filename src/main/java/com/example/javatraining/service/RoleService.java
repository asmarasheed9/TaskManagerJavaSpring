package com.example.javatraining.service;

import com.example.javatraining.model.Role;
import com.example.javatraining.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Optional<Role> findRoleById(Long Id) {
        return roleRepository.findById(Id);
    }

    public List<Role> getAllRoles() { return this.roleRepository.findAll();}

}
