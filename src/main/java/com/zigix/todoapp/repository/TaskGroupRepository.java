package com.zigix.todoapp.repository;

import com.zigix.todoapp.model.TaskGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {

    Page<TaskGroup> findAll(Pageable pageable);

    List<TaskGroup> findAllByCreatedBy(String createdBy);

    List<TaskGroup> findAllByUserUserId(Long userId);

    List<TaskGroup> findAllByUserUserIdAndAndCreatedBy(Long userId, String createdBy);

    Optional<TaskGroup> findById(Long taskGroupId);

    TaskGroup save(TaskGroup taskGroup);

    boolean existsByGroupIdAndCreatedBy(Long groupId, String username);

    void deleteById(Long groupId);
}
