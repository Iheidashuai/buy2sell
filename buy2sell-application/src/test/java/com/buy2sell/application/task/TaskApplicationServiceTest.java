package com.buy2sell.application.task;

import com.buy2sell.domain.task.Task;
import com.buy2sell.domain.task.TaskId;
import com.buy2sell.domain.task.TaskTitle;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskApplicationServiceTest {

    private static final Clock CLOCK = Clock.fixed(Instant.parse("2026-05-06T00:00:00Z"), ZoneOffset.UTC);

    @Test
    void should_create_task() {
        InMemoryRepository repository = new InMemoryRepository();
        TaskApplicationService service = new TaskApplicationService(repository, CLOCK);

        TaskView view = service.create(new CreateTaskCommand("Prepare spec"));

        assertThat(view.id()).isNotBlank();
        assertThat(view.title()).isEqualTo("Prepare spec");
        assertThat(view.status()).isEqualTo("OPEN");
        assertThat(repository.findById(TaskId.of(view.id()))).isPresent();
    }

    @Test
    void should_rename_existing_task() {
        InMemoryRepository repository = new InMemoryRepository();
        Task task = Task.create(TaskId.of("task-1"), TaskTitle.of("Old"), Instant.now(CLOCK));
        repository.save(task);
        TaskApplicationService service = new TaskApplicationService(repository, CLOCK);

        TaskView view = service.rename(new RenameTaskCommand("task-1", "New"));

        assertThat(view.title()).isEqualTo("New");
    }

    @Test
    void should_throw_when_task_not_found() {
        TaskApplicationService service = new TaskApplicationService(new InMemoryRepository(), CLOCK);

        assertThatThrownBy(() -> service.get("missing"))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task not found: missing");
    }

    private static final class InMemoryRepository implements TaskRepository {

        private final Map<TaskId, Task> tasks = new HashMap<>();

        @Override
        public void save(Task task) {
            tasks.put(task.id(), task);
        }

        @Override
        public Optional<Task> findById(TaskId taskId) {
            return Optional.ofNullable(tasks.get(taskId));
        }
    }
}
