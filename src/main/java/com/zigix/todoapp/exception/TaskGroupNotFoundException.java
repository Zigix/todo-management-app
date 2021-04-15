package com.zigix.todoapp.exception;

public class TaskGroupNotFoundException extends RuntimeException {

    public TaskGroupNotFoundException(String message) {
        super(message);
    }
}
