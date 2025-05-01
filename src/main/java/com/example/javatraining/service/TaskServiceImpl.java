package com.example.javatraining.service;

import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.model.Task;
import com.example.javatraining.model.User;
import com.example.javatraining.repository.TaskRepository;
import com.example.javatraining.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        User user = userRepository.findById(taskDto.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("User not found With Given Id"));

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setAssignedUser(user);

        log.info("Task: " + task.toString());
        Task savedTask = taskRepository.save(task);
        log.info("Saved Task: " + savedTask.toString());
        return savedTask;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID " + id));
    }

    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        Task taskToUpdate = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID " + id));

        taskToUpdate.setTitle(taskDto.getTitle());
        taskToUpdate.setDescription(taskDto.getDescription());
        taskToUpdate.setStatus(taskDto.getStatus());
        taskToUpdate.setDueDate(taskDto.getDueDate());

        if (!taskToUpdate.getAssignedUser().getId().equals(taskDto.getAssignedUserId())) {
            User newUser = userRepository.findById(taskDto.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID " + taskDto.getAssignedUserId()));
            taskToUpdate.setAssignedUser(newUser);
        }

        return taskRepository.save(taskToUpdate);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID " + id));
        taskRepository.delete(task);
    }
}
