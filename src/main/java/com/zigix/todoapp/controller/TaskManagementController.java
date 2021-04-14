package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.service.TaskService;
import com.zigix.todoapp.service.management.TaskManagementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/management/tasks")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class TaskManagementController {

    private final TaskManagementService taskManagementService;

    public TaskManagementController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @GetMapping
    public List<Task> readTasksFromUser(@RequestParam("userId") Long userId) {
        return taskManagementService.getTasksByUserId(userId);
    }

    @GetMapping("/{taskId}")
    public Task readSingleTask(@PathVariable("taskId") Long taskId) {
        return taskManagementService.getSingleTask(taskId);
    }

    @PostMapping
    public Task addTaskToUser(@RequestParam("userId") Long userId, @RequestBody Task task) {
        return taskManagementService.addTaskToUser(userId, task);
    }
}
