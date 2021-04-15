package com.zigix.todoapp.service.impl;

import com.zigix.todoapp.exception.TaskAccessException;
import com.zigix.todoapp.exception.TaskNotFoundException;
import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.repository.TaskRepository;
import com.zigix.todoapp.service.TaskService;
import com.zigix.todoapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public List<Task> getTasks() {
        Long userId = getCurrentlyLoggedUserId();
        return taskRepository.findAllByUserUserId(userId);
    }

    @Override
    public Task getSingleTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));

        Long userId = getCurrentlyLoggedUserId();

        if(!task.getUser().getUserId().equals(userId)) {
            throw new TaskAccessException("You have no access to see this task");
        }

        return task;
    }

    @Override
    public Task addTask(Task task) {
        User user = userService.getCurrentlyLoggedUser();
        task.setUser(user);
        task.setTaskId(0L);
        return taskRepository.save(task);
    }

    @Override
    public void toggleTask(Long taskId) {
        Task task = getSingleTask(taskId);
        task.setDone(!task.isDone());
        taskRepository.save(task);
    }

    private Long getCurrentlyLoggedUserId() {
        return userService.getCurrentlyLoggedUser().getUserId();
    }
}
