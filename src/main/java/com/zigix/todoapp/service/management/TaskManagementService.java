package com.zigix.todoapp.service.management;

import com.zigix.todoapp.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public interface TaskManagementService {

    List<Task> getAllTasks(Pageable pageable);

    List<Task> getTasksByUserId(Long userId);

    Task getSingleTask(Long taskId);

    Task addTaskToUser(Long userId, Task task);

    void deleteTaskFromUser(Long taskId);

}
