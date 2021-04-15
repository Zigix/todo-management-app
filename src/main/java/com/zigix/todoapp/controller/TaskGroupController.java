package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.service.TaskGroupService;
import com.zigix.todoapp.service.projection.TaskGroupReadModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class TaskGroupController {

    private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @GetMapping
    public List<TaskGroupReadModel> readAllTaskGroups() {
        return taskGroupService.getTaskGroups().stream()
                .map(TaskGroupReadModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{groupId}")
    public TaskGroupReadModel readSingleTaskGroup(@PathVariable("groupId") Long groupId) {
        TaskGroup taskGroup = taskGroupService.getSingleTaskGroup(groupId);
        return new TaskGroupReadModel(taskGroup);
    }

    @PostMapping
    public TaskGroupReadModel addTaskGroup(@RequestBody TaskGroup toSave) {
        TaskGroup taskGroup = taskGroupService.addTaskGroup(toSave);
        return new TaskGroupReadModel(taskGroup);
    }
}
