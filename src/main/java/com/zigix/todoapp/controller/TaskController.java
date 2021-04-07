package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<Task> readAllTasksFromLoggedUser(@AuthenticationPrincipal(expression = "userId") Long userId) {
        return taskService.getTasks(userId);
    }

    @GetMapping("/{id}")
    public Task readTaskFromLoggedUser(@PathVariable("id") Long taskId,
                                       @AuthenticationPrincipal(expression = "userId") Long userId) {
        return taskService.getSingleTask(taskId, userId);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task,
                        @AuthenticationPrincipal(expression = "userId") Long userId) {
        return taskService.addTask(task, userId);
    }
}