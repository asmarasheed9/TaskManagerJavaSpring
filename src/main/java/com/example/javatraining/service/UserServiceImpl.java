package com.example.javatraining.service;

import com.example.javatraining.dto.AuthRequest;
import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.Role;
import com.example.javatraining.model.User;
import com.example.javatraining.repository.UserRepository;
import com.example.javatraining.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, UserMapper userMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))
        );
    }

    @Override
    public User registerUser(UserDto userDto) {
        Role role = this.roleService.findRoleByName(userDto.getRoleName()).orElseThrow(() -> new RuntimeException("Wrong role name"));
        User user = this.userMapper.toEntity(userDto);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }


    public String loginUser(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Invalid credentials");
          //  return ResponseEntity.ok(new AuthResponse("Login Successful", token));
        }

        return "Welcome";
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findUserByUuidId(String uuId) {
        return userRepository.findByUuid(uuId)
                .orElseThrow(() -> new RuntimeException("User not found with ID " + uuId));
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
