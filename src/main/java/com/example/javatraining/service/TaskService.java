package com.example.javatraining.service;

import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.model.Task;
import com.example.javatraining.model.User;
import org.springframework.data.domain.Page;


public interface TaskService {
    Page<TaskDto> getTasksForCurrentUser(User currentUser, int page, String sortBy);
    Task getTaskForCurrentUser(Long taskId, User currentUser);
    Task updateTaskStatus(Long taskId, String newStatus, User currentUser);
    Task createTask(Task task, User currentUser);
    Task assignTask(Long taskId, String userUuiId, User currentUser);
    void deleteTask(Long taskId, User currentUser);
}
