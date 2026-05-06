package com.buy2sell.domain.task;

import java.time.Instant;
import java.util.Objects;

public final class Task {

    private final TaskId id;
    private TaskTitle title;
    private TaskStatus status;
    private final Instant createdAt;
    private Instant completedAt;

    private Task(TaskId id, TaskTitle title, TaskStatus status, Instant createdAt, Instant completedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.completedAt = completedAt;
    }

    public static Task create(TaskId id, TaskTitle title, Instant now) {
        return new Task(id, title, TaskStatus.OPEN, now, null);
    }

    public void rename(TaskTitle newTitle) {
        ensureOpen("Completed task cannot be renamed");
        this.title = Objects.requireNonNull(newTitle, "newTitle must not be null");
    }

    public void complete(Instant now) {
        ensureOpen("Task is already completed");
        this.status = TaskStatus.COMPLETED;
        this.completedAt = Objects.requireNonNull(now, "now must not be null");
    }

    private void ensureOpen(String message) {
        if (status != TaskStatus.OPEN) {
            throw new InvalidTaskStateException(message);
        }
    }

    public TaskId id() {
        return id;
    }

    public TaskTitle title() {
        return title;
    }

    public TaskStatus status() {
        return status;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant completedAt() {
        return completedAt;
    }
}
