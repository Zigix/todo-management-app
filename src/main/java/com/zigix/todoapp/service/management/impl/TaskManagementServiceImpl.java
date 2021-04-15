package com.zigix.todoapp.service.management.impl;

import com.zigix.todoapp.exception.TaskAccessException;
import com.zigix.todoapp.exception.TaskNotFoundException;
import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.repository.TaskRepository;
import com.zigix.todoapp.service.UserService;
import com.zigix.todoapp.service.management.TaskManagementService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskManagementServiceImpl(TaskRepository taskRepository,
                                     UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }


    @Override
    public List<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        String username = getCurrentlyLoggedUserUsername();
        return taskRepository.findAllByUserUserIdAndCreatedBy(userId, username);
    }

    @Override
    public Task getSingleTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));

        String username = getCurrentlyLoggedUserUsername();

        if(!task.getCreatedBy().equals(username)) {
            throw new TaskAccessException("You have no access to see this task");
        }

        return task;
    }

    @Override
    public Task addTaskToUser(Long userId, Task task) {
        User user = userService.getUserById(userId);
        task.setUser(user);
        task.setTaskId(0L);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskFromUser(Long taskId) {
        String username = getCurrentlyLoggedUserUsername();
        boolean canDelete = taskRepository.existsByTaskIdAndCreatedBy(taskId, username);

        if(!canDelete) {
            throw new TaskAccessException("You have no access to delete this task");
        }

        taskRepository.deleteById(taskId);
    }

    private String getCurrentlyLoggedUserUsername() {
        return userService.getCurrentlyLoggedUser().getUsername();
    }

}
