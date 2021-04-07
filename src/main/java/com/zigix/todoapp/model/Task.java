package com.zigix.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zigix.todoapp.audit.Audit;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties("taskGroup")
public class Task extends Audit<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long taskId;
    private String description;
    private LocalDateTime deadline;
    private boolean done = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup taskGroup;


    public Task() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }
}

