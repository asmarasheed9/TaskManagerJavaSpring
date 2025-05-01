package com.example.javatraining.service;

import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskDto taskDto);
    List<Task> findAllTasks();
    List<Task> getTasksByUserId(Long userId);
    Task getTaskById(Long id);
    Task updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
}
