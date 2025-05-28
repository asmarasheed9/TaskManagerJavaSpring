package com.example.javatraining.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    // @Schema(description = "Unique identifier of the task.", required = true)
    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();;

    @Column(unique = true, nullable = false)
    private String title;

    private String description;

    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User assignedUser;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }

    public Task() {}

    public Task(String title, String description, LocalDateTime dueDate, User user) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.assignedUser = user;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
