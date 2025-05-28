package com.example.javatraining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private String status;

    @NotNull(message = "Assigned user is required")
    private Long assignedUserId;

    private String uuid;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
