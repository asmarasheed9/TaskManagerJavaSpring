package com.example.javatraining.service;

import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.exception.ValidationExceptionHandler;
import com.example.javatraining.model.Task;
import com.example.javatraining.model.User;
import com.example.javatraining.repository.TaskRepository;
import com.example.javatraining.service.mapper.TaskMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }

    @Override
    public Page<TaskDto> getTasksForCurrentUser(User currentUser, int page, String sortBy) {
        if (isAdmin(currentUser)) {
            Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
            Page<Task> tasks = taskRepository.findAll(pageable);
            return tasks.map(taskMapper::toDto);
        }

        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        Page<Task> tasks = taskRepository.findByAssignedUserId(currentUser.getId(), pageable);
        return tasks.map(taskMapper::toDto);
    }

    @Override
    public Task getTaskForCurrentUser(Long taskId, User currentUser) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ValidationExceptionHandler.ResourceNotFoundException("Task not found"));
            if (!isAdmin(currentUser) && !task.getAssignedUser().getId().equals(currentUser.getId())) {
                throw new AccessDeniedException("Access denied");
            }
            return task;
        } catch (AccessDeniedException ex) {
            System.out.println("Access denied: " + ex.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public Task updateTaskStatus(Long taskId, String newStatus, User currentUser) {
        try {
            Task task = getTaskForCurrentUser(taskId, currentUser);
            task.setStatus(newStatus);
            return taskRepository.save(task);
        } catch (Exception ex) {
            System.out.println("Exception Occur: " + ex.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public Task createTask(Task task, User currentUser) {
        try {
            requireAdmin(currentUser);
            return taskRepository.save(task);
        } catch (Exception ex) {
            System.out.println("Exception Occur: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Task assignTask(Long taskId, String userUuiId, User currentUser) {
        try {
            requireAdmin(currentUser);
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ValidationExceptionHandler.ResourceNotFoundException("Task not found"));
            User assignee = userService.findUserByUuidId(userUuiId);
            task.setAssignedUser(assignee);
            return taskRepository.save(task);
        } catch (Exception ex) {
            System.out.println("Exception Occur: " + ex.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId, User currentUser) {
        try {
            this.requireAdmin(currentUser);
            taskRepository.deleteById(taskId);
        } catch (Exception ex) {
            System.out.println("Exception Occur: " + ex.getMessage());
        }
    }

    private void requireAdmin(User user) throws AccessDeniedException {
        if (!isAdmin(user)) {
            throw new AccessDeniedException("Admin privileges required");
        }
    }


    private boolean isAdmin(User user) {
        return user.getRole().getName().equalsIgnoreCase("ADMIN");
    }
}
