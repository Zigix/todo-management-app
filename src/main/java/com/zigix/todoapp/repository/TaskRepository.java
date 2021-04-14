package com.zigix.todoapp.repository;

import com.zigix.todoapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    List<Task> findAllByUserUserId(Long userId);

    List<Task> findAllByUserUserIdAndCreatedBy(Long userId, String taskCreatorName);

    Page<Task> findAll(Pageable pageable);

    Optional<Task> findById(Long taskId);

    Task save(Task task);

    void deleteById(Long taskId);

    boolean existsByTaskIdAndCreatedBy(Long taskId, String taskCreatorName);

}
