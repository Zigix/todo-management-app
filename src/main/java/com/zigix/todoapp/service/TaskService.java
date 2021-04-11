package com.zigix.todoapp.service;

import com.zigix.todoapp.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    List<Task> getTasks(Long userId, String taskCreatorName);

    Task getSingleTask(Long taskId);

    Task getSingleTask(Long taskId, String taskCreatorName);

    Task addTask(Task task);

    Task addTask(Task task, Long userId);

}
