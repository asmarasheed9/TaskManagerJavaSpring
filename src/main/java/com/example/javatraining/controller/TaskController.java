package com.example.javatraining.controller;

import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.model.Task;
import com.example.javatraining.service.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@Valid @RequestBody TaskDto taskDto) {
        Task createdTask = taskService.createTask(taskDto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", createdTask.getId());
        response.put("title", createdTask.getTitle());
        response.put("description", createdTask.getDescription());
        response.put("dueDate", createdTask.getDueDate());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllTasks() {
        List<Map<String, Object>> listOfTasks = new ArrayList<>();

        taskService.findAllTasks().forEach(task -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", task.getId());
            response.put("title", task.getTitle());
            response.put("description", task.getDescription());
            response.put("dueDate", task.getDueDate());
            listOfTasks.add(response);
        });
        return ResponseEntity.ok(listOfTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTaskById(@PathVariable Long id) {

        Task createdTask = taskService.getTasksByUserId(id).get(0);
        Map<String, Object> response = new HashMap<>();
        response.put("id", createdTask.getId());
        response.put("title", createdTask.getTitle());
        response.put("description", createdTask.getDescription());
        response.put("dueDate", createdTask.getDueDate());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}/tasks")
    public ResponseEntity<List<Map<String, Object>>> getTasksByUserId(@PathVariable Long id) {
        List<Map<String, Object>> listOfTasks = new ArrayList<>();

        taskService.getTasksByUserId(id).forEach(task -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", task.getId());
            response.put("title", task.getTitle());
            response.put("description", task.getDescription());
            response.put("dueDate", task.getDueDate());
            listOfTasks.add(response);
        });
        return ResponseEntity.ok(listOfTasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedTask.getId());
        response.put("title", updatedTask.getTitle());
        response.put("description", updatedTask.getDescription());
        response.put("dueDate", updatedTask.getDueDate());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
