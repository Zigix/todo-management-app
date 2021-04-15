package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.service.management.TaskGroupManagementService;
import com.zigix.todoapp.service.projection.TaskGroupReadModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/groups")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class TaskGroupManagementController {

    private final TaskGroupManagementService taskGroupManagementService;

    public TaskGroupManagementController(TaskGroupManagementService taskGroupManagementService) {
        this.taskGroupManagementService = taskGroupManagementService;
    }


    @GetMapping
    public List<TaskGroupReadModel> readAllTaskGroupsFromUser(@RequestParam("userId") Long userId) {
        return taskGroupManagementService.getTaskGroupsByUserId(userId).stream()
                .map(TaskGroupReadModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{groupId}")
    public TaskGroupReadModel readSingleTaskGroup(@PathVariable("groupId") Long groupId) {
        TaskGroup taskGroup = taskGroupManagementService.getSingleTaskGroup(groupId);
        return new TaskGroupReadModel(taskGroup);
    }

    @PostMapping
    public TaskGroupReadModel addTaskGroupToUser(@RequestParam("userId") Long userId,
                                        @RequestBody TaskGroup toSave) {
        TaskGroup taskGroup = taskGroupManagementService.addTaskGroupToUser(userId, toSave);
        return new TaskGroupReadModel(taskGroup);
    }

}
