package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.service.TaskService;
import com.zigix.todoapp.service.management.TaskManagementService;
import com.zigix.todoapp.service.projection.TaskReadModel;
import com.zigix.todoapp.service.projection.TaskWriteModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/tasks")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class TaskManagementController {

    private final TaskManagementService taskManagementService;

    public TaskManagementController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @GetMapping
    public List<TaskReadModel> readTasksFromUser(@RequestParam("userId") Long userId) {
        return taskManagementService.getTasksByUserId(userId).stream()
                .map(TaskReadModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{taskId}")
    public TaskReadModel readSingleTask(@PathVariable("taskId") Long taskId) {
        Task task = taskManagementService.getSingleTask(taskId);
        return new TaskReadModel(task);
    }

    @PostMapping
    public TaskReadModel addTaskToUser(@RequestParam("userId") Long userId,
                                       @RequestBody @Valid TaskWriteModel taskWriteModel) {
        Task task =  taskManagementService.addTaskToUser(userId, taskWriteModel.toTask());
        return new TaskReadModel(task);
    }
}
