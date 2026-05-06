package com.buy2sell.domain.task;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskTest {

    private static final Instant NOW = Instant.parse("2026-05-06T00:00:00Z");

    @Test
    void should_create_open_task() {
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Prepare spec"), NOW);

        assertThat(task.id()).isEqualTo(TaskId.of("task-1"));
        assertThat(task.title()).isEqualTo(TaskTitle.of("Prepare spec"));
        assertThat(task.status()).isEqualTo(TaskStatus.OPEN);
        assertThat(task.createdAt()).isEqualTo(NOW);
        assertThat(task.completedAt()).isNull();
    }

    @Test
    void should_rename_open_task() {
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Old title"), NOW);

        task.rename(TaskTitle.of("New title"));

        assertThat(task.title()).isEqualTo(TaskTitle.of("New title"));
    }

    @Test
    void should_complete_open_task() {
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Prepare spec"), NOW);
        Instant completedAt = NOW.plusSeconds(60);

        task.complete(completedAt);

        assertThat(task.status()).isEqualTo(TaskStatus.COMPLETED);
        assertThat(task.completedAt()).isEqualTo(completedAt);
    }

    @Test
    void should_not_rename_completed_task() {
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Prepare spec"), NOW);
        task.complete(NOW.plusSeconds(60));

        assertThatThrownBy(() -> task.rename(TaskTitle.of("New title")))
                .isInstanceOf(InvalidTaskStateException.class)
                .hasMessage("Completed task cannot be renamed");
    }

    @Test
    void should_not_complete_task_twice() {
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Prepare spec"), NOW);
        task.complete(NOW.plusSeconds(60));

        assertThatThrownBy(() -> task.complete(NOW.plusSeconds(120)))
                .isInstanceOf(InvalidTaskStateException.class)
                .hasMessage("Task is already completed");
    }
}
