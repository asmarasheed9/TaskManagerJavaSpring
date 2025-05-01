package com.example.javatraining.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private String status;

    private LocalDateTime dueDate;

    @NotNull(message = "Assigned user is required")
    private Long assignedUserId;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
