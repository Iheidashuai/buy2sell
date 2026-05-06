package com.buy2sell.bootstrap;

import com.buy2sell.adapter.task.TaskFacade;
import com.buy2sell.application.task.TaskView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Buy2SellBootstrapTest {

    @Test
    void should_wire_task_facade() {
        TaskFacade facade = Buy2SellBootstrap.taskFacade();

        TaskView task = facade.createTask("Smoke test");

        assertThat(task.id()).isNotBlank();
        assertThat(task.title()).isEqualTo("Smoke test");
    }
}
