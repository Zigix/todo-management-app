package com.zigix.todoapp.service;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.model.TaskGroup;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final UserService userService;

    public TaskGroupServiceImpl(TaskGroupRepository taskGroupRepository, UserService userService) {
        this.taskGroupRepository = taskGroupRepository;
        this.userService = userService;
    }

    @Override
    public List<TaskGroup> getTaskGroups(Long userId) {
        return taskGroupRepository.findAllByUserUserId(userId);
    }

    @Override
    public List<TaskGroup> getTaskGroups(Long userId, String creatorName) {
        return taskGroupRepository.findAllByUserUserIdAndAndCreatedBy(userId, creatorName);
    }

    @Override
    public TaskGroup getSingleTaskGroup(Long groupId, Long userId) {
        TaskGroup taskGroup = taskGroupRepository.findById(groupId)
                .orElseThrow(); // FIXME: ...

        if(!taskGroup.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("u hv not access");
        }

        return taskGroup;
    }

    @Override
    public TaskGroup getSingleTaskGroup(Long groupId, String creatorName) {
        TaskGroup taskGroup = taskGroupRepository.findById(groupId)
                .orElseThrow(); // FIXME: ...

        if(!taskGroup.getCreatedBy().equals(creatorName)) {
            throw new IllegalStateException(); // FIXME: ...
        }

        return taskGroup;
    }

    @Override
    public TaskGroup addTaskGroup(TaskGroup taskGroup, Long userId) {
        User user = userService.getUserById(userId);

        taskGroup.setUser(user);
        taskGroup.getTasks().stream()
                .map(Task::getDeadline)
                .filter(Objects::nonNull)
                .max(LocalDateTime::compareTo)
                .ifPresent(taskGroup::setDeadline);

        taskGroup.getTasks().forEach(task -> {
            task.setUser(user);
        });

        return taskGroupRepository.save(taskGroup);
    }
}
