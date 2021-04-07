package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.service.TaskGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/management/groups")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class TaskGroupManagementController {

    private final TaskGroupService taskGroupService;

    public TaskGroupManagementController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @GetMapping
    public List<TaskGroup> readAllTaskGroups(@RequestParam("userId") Long userId,
                                             Principal principal) {
        return taskGroupService.getTaskGroups(userId, principal.getName());
    }

    @GetMapping("/{groupId}")
    public TaskGroup readSingleTaskGroup(@PathVariable("groupId") Long groupId,
                                         Principal principal) {
        return taskGroupService.getSingleTaskGroup(groupId, principal.getName());
    }

    @PostMapping
    public TaskGroup addTaskGroupToUser(@RequestParam("userId") Long userId,
                                        @RequestBody TaskGroup taskGroup) {
        return taskGroupService.addTaskGroup(taskGroup, userId);
    }
}
