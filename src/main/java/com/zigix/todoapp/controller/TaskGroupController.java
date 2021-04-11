package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.service.TaskGroupService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class TaskGroupController {

    private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @GetMapping
    public List<TaskGroup> readAllTaskGroups() {
        return taskGroupService.getTaskGroups();
    }

    @GetMapping("/{groupId}")
    public TaskGroup readSingleTaskGroup(@PathVariable("groupId") Long groupId) {
        return taskGroupService.getSingleTaskGroup(groupId);
    }

    @PostMapping
    public TaskGroup addTaskGroup(@RequestBody TaskGroup taskGroup) {
        return taskGroupService.addTaskGroup(taskGroup);
    }
}
