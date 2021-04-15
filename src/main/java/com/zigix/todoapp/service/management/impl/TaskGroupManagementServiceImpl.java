package com.zigix.todoapp.service.management.impl;

import com.zigix.todoapp.exception.TaskGroupAccessException;
import com.zigix.todoapp.exception.TaskGroupNotFoundException;
import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.repository.TaskGroupRepository;
import com.zigix.todoapp.service.UserService;
import com.zigix.todoapp.service.management.TaskGroupManagementService;
import com.zigix.todoapp.service.management.TaskManagementService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupManagementServiceImpl implements TaskGroupManagementService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskManagementService taskManagementService;
    private final UserService userService;

    public TaskGroupManagementServiceImpl(TaskGroupRepository taskGroupRepository,
                                          TaskManagementService taskManagementService,
                                          UserService userService) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskManagementService = taskManagementService;
        this.userService = userService;
    }

    @Override
    public List<TaskGroup> getAllTaskGroups(Pageable pageable) {
        return taskGroupRepository.findAll(pageable).getContent();
    }

    @Override
    public List<TaskGroup> getTaskGroupsByUserId(Long userId) {
        String username = getCurrentlyLoggedUsername();
        return taskGroupRepository.findAllByUserUserIdAndAndCreatedBy(userId, username);
    }

    @Override
    public TaskGroup getSingleTaskGroup(Long groupId) {
        TaskGroup taskGroup = taskGroupRepository.findById(groupId)
                .orElseThrow(() -> new TaskGroupNotFoundException("Group with id " + groupId + " not found"));

        String username = getCurrentlyLoggedUsername();

        if(!taskGroup.getCreatedBy().equals(username)) {
            throw new TaskGroupAccessException("You have no access to see this group");
        }

        return taskGroup;
    }

    @Override
    public TaskGroup addTaskGroupToUser(Long userId, TaskGroup taskGroup) {
        User user = userService.getUserById(userId);
        taskGroup.setUser(user);

        taskGroup.getTasks().forEach(task -> {
            task.setUser(user);
            task.setTaskGroup(taskGroup);
        });

        return taskGroup;
    }

    @Override
    public void deleteTaskGroupFromUser(Long groupId) {
        String username = getCurrentlyLoggedUsername();
        boolean canDelete = taskGroupRepository.existsByGroupIdAndCreatedBy(groupId, username);

        if(!canDelete) {
            throw new TaskGroupAccessException("You have no access to this group");
        }

        taskGroupRepository.deleteById(groupId);
    }

    private String getCurrentlyLoggedUsername() {
        return userService.getCurrentlyLoggedUser().getUsername();
    }
}
