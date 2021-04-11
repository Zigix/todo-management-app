package com.zigix.todoapp.service;

import com.zigix.todoapp.model.TaskGroup;

import java.util.List;

public interface TaskGroupService {

    List<TaskGroup> getTaskGroups();

    List<TaskGroup> getTaskGroups(Long userId, String creatorName);

    TaskGroup getSingleTaskGroup(Long groupId);

    TaskGroup getSingleTaskGroup(Long groupId, String creatorName);

    TaskGroup addTaskGroup(TaskGroup taskGroup);

    TaskGroup addTaskGroup(TaskGroup taskGroup, Long userId);
}
