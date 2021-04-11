package com.zigix.todoapp.service;

import com.zigix.todoapp.model.Task;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.repository.TaskRepository;
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
        Long userId = userService.getCurrentlyLoggedUser().getUserId();
        return taskRepository.findAllByUserUserId(userId);
    }

    @Override
    public List<Task> getTasks(Long userId, String taskCreatorName) {
        return taskRepository.findAllByUserUserIdAndCreatedBy(userId, taskCreatorName);
    }

    @Override
    public Task getSingleTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(); // FIXME: ...

        Long userId = userService.getCurrentlyLoggedUser().getUserId();

        if(!task.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException(); // FIXME: ...
        }

        return task;
    }

    @Override
    public Task getSingleTask(Long taskId, String taskCreatorName) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(); // FIXME: ...

        if(!task.getCreatedBy().equals(taskCreatorName)) {
            throw new IllegalStateException(); // FIXME ...
        }

        return task;
    }

    @Override
    public Task addTask(Task task) {
        Long userId = userService.getCurrentlyLoggedUser().getUserId();
        return addTask(task, userId);
    }

    @Override
    public Task addTask(Task task, Long userId) {
        User user = userService.getUserById(userId);
        task.setUser(user);
        return taskRepository.save(task);
    }
}
