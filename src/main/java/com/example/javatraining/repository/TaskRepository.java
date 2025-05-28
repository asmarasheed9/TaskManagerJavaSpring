package com.example.javatraining.repository;

import com.example.javatraining.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUserId(Long userId);
    Page<Task> findByAssignedUserId(Long assignedUserId, Pageable pageable);
}
