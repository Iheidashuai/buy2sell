package com.buy2sell.domain.task;

import java.util.Objects;
import java.util.UUID;

public final class TaskId {

    private final String value;

    private TaskId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("TaskId must not be blank");
        }
        this.value = value;
    }

    public static TaskId newId() {
        return new TaskId(UUID.randomUUID().toString());
    }

    public static TaskId of(String value) {
        return new TaskId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskId)) {
            return false;
        }
        TaskId taskId = (TaskId) other;
        return Objects.equals(value, taskId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
