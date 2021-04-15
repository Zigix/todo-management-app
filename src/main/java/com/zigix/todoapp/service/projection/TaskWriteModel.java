package com.zigix.todoapp.service.projection;

import com.zigix.todoapp.model.Task;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class TaskWriteModel {

    @NotEmpty
    private String description;
    private LocalDateTime deadline;

    public TaskWriteModel() {
    }

    public Task toTask() {
        Task task = new Task();
        task.setDescription(this.description);
        task.setDeadline(this.deadline);
        return task;
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
}
