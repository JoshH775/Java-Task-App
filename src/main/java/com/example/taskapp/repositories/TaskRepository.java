package com.example.taskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapp.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
