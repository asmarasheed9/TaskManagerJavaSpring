package com.example.javatraining.controller;

import com.example.javatraining.dto.ApiResponse;
import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.model.Task;
import com.example.javatraining.model.User;
import com.example.javatraining.service.TaskService;
import com.example.javatraining.service.UserService;
import com.example.javatraining.service.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public User getUserFromAuthentication(Authentication auth) {
        return (User) auth.getPrincipal();
    }

    @GetMapping
    public ApiResponse<List<TaskDto>> getAllTasks(Authentication authentication,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "id") String sortBy) {
        User currentUser = this.getUserFromAuthentication(authentication);
        return new ApiResponse(true, "List of Tasks", taskService.getTasksForCurrentUser(currentUser, page, sortBy)
                .stream()
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> getTask(@PathVariable Long taskId, Authentication authentication) {
        User currentUser = this.getUserFromAuthentication(authentication);
        return ResponseEntity.ok(new ApiResponse<>(true, "Task retrieved ", taskService.getTaskForCurrentUser(taskId, currentUser)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Task>> updateStatus(@PathVariable Long id, @RequestBody String statusUpdate, Authentication authentication) {
        User currentUser = this.getUserFromAuthentication(authentication);
        return ResponseEntity.ok(new ApiResponse<>(true, "Task updated ", taskService.updateTaskStatus(id, statusUpdate, currentUser)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody Task task, Authentication authentication) {
        User currentUser = this.getUserFromAuthentication(authentication);
        return ResponseEntity.ok(new ApiResponse<>(true, "Task created ", taskService.createTask(task, currentUser)));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<ApiResponse<Task>> assignTask(@PathVariable Long id, @RequestBody String assignUserId, Authentication authentication) {
        User currentUser = this.getUserFromAuthentication(authentication);
        return ResponseEntity.ok(new ApiResponse<>(true, "Task assigned ", taskService.assignTask(id, assignUserId, currentUser)));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, Authentication authentication) {
        User currentUser = this.getUserFromAuthentication(authentication);
        taskService.deleteTask(id, currentUser);
    }
}
