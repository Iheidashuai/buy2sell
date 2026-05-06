package com.buy2sell.domain.task;

import java.util.Objects;

public final class TaskTitle {

    private static final int MAX_LENGTH = 120;

    private final String value;

    private TaskTitle(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title must not be blank");
        }
        String normalized = value.trim();
        if (normalized.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Task title must not exceed " + MAX_LENGTH + " characters");
        }
        this.value = normalized;
    }

    public static TaskTitle of(String value) {
        return new TaskTitle(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskTitle)) {
            return false;
        }
        TaskTitle taskTitle = (TaskTitle) other;
        return Objects.equals(value, taskTitle.value);
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
