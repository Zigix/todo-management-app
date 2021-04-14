package com.zigix.todoapp.service;

import com.zigix.todoapp.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    Task getSingleTask(Long taskId);

    Task addTask(Task task);

    void toggleTask(Long taskId);

}
