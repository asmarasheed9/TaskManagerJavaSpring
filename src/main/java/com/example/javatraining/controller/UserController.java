package com.example.javatraining.controller;
import com.example.javatraining.model.User;
import com.example.javatraining.service.RoleService;
import com.example.javatraining.service.TaskService;
import com.example.javatraining.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final TaskService taskService;

    public UserController(UserService userService, RoleService roleService, TaskService taskService) {
        this.userService = userService;
        this.roleService = roleService;
        this.taskService = taskService;
    }

    @GetMapping("{userId}/tasks")
    public ResponseEntity<?> getTasksByUserId(@PathVariable Long userId) {
        User user = userService.findUserById(userId);

        List<Map<String, Object>> listOfTasks = new ArrayList<>();

        if (user.getRole().getName().equals("ADMIN")) {
            taskService.getTasksByUserId(userId).forEach(task -> {
                Map<String, Object> response = new HashMap<>();
                response.put("id", task.getId());
                response.put("title", task.getTitle());
                response.put("description", task.getDescription());
                response.put("dueDate", task.getDueDate());
                listOfTasks.add(response);
            });
        }
        return ResponseEntity.ok(listOfTasks);
    }

}
