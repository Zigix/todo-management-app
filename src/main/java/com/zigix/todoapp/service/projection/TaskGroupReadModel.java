package com.zigix.todoapp.service.projection;

import com.zigix.todoapp.model.TaskGroup;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskGroupReadModel {

    private String description;
    private LocalDateTime deadline;
    private Set<TaskReadModel> tasks;

    public TaskGroupReadModel(TaskGroup source) {
        this.description = source.getDescription();
        this.deadline = source.getDeadline();
        this.tasks = source.getTasks().stream()
                .map(TaskReadModel::new)
                .collect(Collectors.toSet());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Set<TaskReadModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskReadModel> tasks) {
        this.tasks = tasks;
    }
}
