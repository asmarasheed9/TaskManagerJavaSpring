package com.example.javatraining.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    // @Schema(description = "Unique identifier of the role.", required = true)
    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
