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
    public List<TaskGroup> readAllTaskGroups(@AuthenticationPrincipal(expression = "userId") Long userId) {
        return taskGroupService.getTaskGroups(userId);
    }

    @GetMapping("/{groupId}")
    public TaskGroup readSingleTaskGroup(@PathVariable("groupId") Long groupId,
                                         @AuthenticationPrincipal(expression = "userId") Long userId) {
        return taskGroupService.getSingleTaskGroup(groupId, userId);
    }

    @PostMapping
    public TaskGroup addTaskGroup(@RequestBody TaskGroup taskGroup,
                                  @AuthenticationPrincipal(expression = "userId") Long userId) {
        return taskGroupService.addTaskGroup(taskGroup, userId);
    }
}
