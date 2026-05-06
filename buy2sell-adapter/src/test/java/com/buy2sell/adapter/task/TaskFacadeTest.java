package com.buy2sell.adapter.task;

import com.buy2sell.application.task.TaskApplicationService;
import com.buy2sell.application.task.TaskView;
import com.buy2sell.infrastructure.task.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class TaskFacadeTest {

    @Test
    void should_delegate_task_operations() {
        TaskApplicationService service = new TaskApplicationService(
                new InMemoryTaskRepository(),
                Clock.fixed(Instant.parse("2026-05-06T00:00:00Z"), ZoneOffset.UTC)
        );
        TaskFacade facade = new TaskFacade(service);

        TaskView created = facade.createTask("Prepare spec");
        TaskView renamed = facade.renameTask(created.id(), "Prepare plan");
        TaskView completed = facade.completeTask(created.id());

        assertThat(renamed.title()).isEqualTo("Prepare plan");
        assertThat(completed.status()).isEqualTo("COMPLETED");
    }
}
