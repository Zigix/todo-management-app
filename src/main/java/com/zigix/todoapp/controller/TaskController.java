package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> readAllTasksFromLoggedUser() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task readTaskFromLoggedUser(@PathVariable("id") Long taskId) {
        return taskService.getSingleTask(taskId);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }
}