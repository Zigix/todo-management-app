package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/management/tasks")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class TaskManagementController {

    private final TaskService taskService;

    public TaskManagementController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> readTasksFromUser(@RequestParam("userId") Long userId, Principal principal) {
        return taskService.getTasks(userId, principal.getName());
    }

    @GetMapping("/{taskId}")
    public Task readSingleTask(@PathVariable("taskId") Long taskId, Principal principal) {
        return taskService.getSingleTask(taskId, principal.getName());
    }

    @PostMapping
    public Task addTaskToUser(@RequestParam("userId") Long userId, @RequestBody Task task) {
        return taskService.addTask(task, userId);
    }
}
