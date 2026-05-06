package com.buy2sell.application.task;

public final class CompleteTaskCommand {

    private final String taskId;

    public CompleteTaskCommand(String taskId) {
        this.taskId = taskId;
    }

    public String taskId() {
        return taskId;
    }
}
