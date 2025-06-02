package com.example.javatraining.config;

import com.example.javatraining.model.Role;
import com.example.javatraining.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                roleRepository.save(new Role("ADMIN", UUID.randomUUID().toString()));
            }
            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(new Role("USER", UUID.randomUUID().toString()));
            }
        };
    }
}
