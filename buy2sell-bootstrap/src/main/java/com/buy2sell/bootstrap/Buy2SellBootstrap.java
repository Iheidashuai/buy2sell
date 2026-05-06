package com.buy2sell.bootstrap;

import com.buy2sell.adapter.task.TaskFacade;
import com.buy2sell.application.task.TaskApplicationService;
import com.buy2sell.infrastructure.task.InMemoryTaskRepository;

import java.time.Clock;

public final class Buy2SellBootstrap {

    private Buy2SellBootstrap() {
    }

    public static TaskFacade taskFacade() {
        return new TaskFacade(new TaskApplicationService(new InMemoryTaskRepository(), Clock.systemUTC()));
    }
}
