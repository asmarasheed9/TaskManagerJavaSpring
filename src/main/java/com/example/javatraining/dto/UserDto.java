package com.example.javatraining.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Role name is required")
    private String roleName;

    private String uuId;

    public String getEmail() {
        return email;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuId;
    }

    public void setUuid(String uuid) {
        this.uuId = uuid;
    }
}
