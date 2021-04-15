package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.service.TaskService;
import com.zigix.todoapp.service.projection.TaskReadModel;
import com.zigix.todoapp.service.projection.TaskWriteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskReadModel> readAllTasksFromLoggedUser() {
        return taskService.getTasks().stream()
                .map(TaskReadModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskReadModel readTaskFromLoggedUser(@PathVariable("id") Long taskId) {
        Task task =  taskService.getSingleTask(taskId);
        return new TaskReadModel(task);
    }

    @PostMapping
    public TaskReadModel addTask(@RequestBody @Valid TaskWriteModel taskWriteModel) {
        Task task = taskService.addTask(taskWriteModel.toTask());
        return new TaskReadModel(task);
    }
}