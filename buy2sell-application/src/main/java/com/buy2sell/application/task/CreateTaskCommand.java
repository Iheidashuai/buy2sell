package com.buy2sell.application.task;

public final class CreateTaskCommand {

    private final String title;

    public CreateTaskCommand(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
