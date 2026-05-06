package com.buy2sell.application.task;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String taskId) {
        super("Task not found: " + taskId);
    }
}
