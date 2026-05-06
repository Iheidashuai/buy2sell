package com.buy2sell.application.task;

public final class RenameTaskCommand {

    private final String taskId;
    private final String newTitle;

    public RenameTaskCommand(String taskId, String newTitle) {
        this.taskId = taskId;
        this.newTitle = newTitle;
    }

    public String taskId() {
        return taskId;
    }

    public String newTitle() {
        return newTitle;
    }
}
