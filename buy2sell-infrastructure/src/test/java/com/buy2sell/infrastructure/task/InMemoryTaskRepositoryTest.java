package com.buy2sell.infrastructure.task;

import com.buy2sell.domain.task.Task;
import com.buy2sell.domain.task.TaskId;
import com.buy2sell.domain.task.TaskTitle;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryTaskRepositoryTest {

    @Test
    void should_save_and_find_task() {
        InMemoryTaskRepository repository = new InMemoryTaskRepository();
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Prepare spec"), Instant.parse("2026-05-06T00:00:00Z"));

        repository.save(task);

        assertThat(repository.findById(TaskId.of("task-1"))).contains(task);
        assertThat(repository.size()).isEqualTo(1);
    }

    @Test
    void should_return_empty_when_task_missing() {
        InMemoryTaskRepository repository = new InMemoryTaskRepository();

        assertThat(repository.findById(TaskId.of("missing"))).isEmpty();
    }
}
