package com.zigix.todoapp.service.projection;

import com.zigix.todoapp.model.Task;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskReadModel {

    private Long taskId;
    private String description;
    private LocalDateTime deadline;
    private boolean done;
    private Long groupId;
    private String owner;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public TaskReadModel(Task source) {
        this.taskId = source.getTaskId();
        this.description = source.getDescription();
        this.deadline = source.getDeadline();
        this.done = source.isDone();
        this.owner = source.getUser().getUsername();
        this.createdBy = source.getCreatedBy();
        this.createdDate = source.getCreatedDate();
        this.lastModifiedBy = source.getLastModifiedBy();
        this.lastModifiedDate = source.getLastModifiedDate();

        if(source.getTaskGroup() != null) {
            this.groupId = source.getTaskGroup().getGroupId();
        }
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
