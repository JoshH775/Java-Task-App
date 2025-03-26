package com.example.taskapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.taskapp.models.Task;
import com.example.taskapp.repositories.TaskRepository;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}