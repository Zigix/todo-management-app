package com.zigix.todoapp.repository;

import com.zigix.todoapp.model.TaskGroup;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {

    List<TaskGroup> findAllByUserUserId(Long userId);

    List<TaskGroup> findAllByUserUserIdAndAndCreatedBy(Long userId, String createdBy);

    Optional<TaskGroup> findById(Long taskGroupId);

    TaskGroup save(TaskGroup taskGroup);
}
