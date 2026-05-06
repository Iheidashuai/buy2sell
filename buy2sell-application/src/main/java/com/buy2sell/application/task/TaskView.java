package com.buy2sell.application.task;

import com.buy2sell.domain.task.Task;

import java.time.Instant;

public final class TaskView {

    private final String id;
    private final String title;
    private final String status;
    private final Instant createdAt;
    private final Instant completedAt;

    private TaskView(String id, String title, String status, Instant createdAt, Instant completedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public static TaskView from(Task task) {
        return new TaskView(
                task.id().value(),
                task.title().value(),
                task.status().name(),
                task.createdAt(),
                task.completedAt()
        );
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String status() {
        return status;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant completedAt() {
        return completedAt;
    }
}
