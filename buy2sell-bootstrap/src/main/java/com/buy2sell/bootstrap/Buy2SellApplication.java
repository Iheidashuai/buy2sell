package com.buy2sell.bootstrap;

import com.buy2sell.adapter.task.TaskFacade;
import com.buy2sell.application.task.TaskView;

public final class Buy2SellApplication {

    private Buy2SellApplication() {
    }

    public static void main(String[] args) {
        TaskFacade taskFacade = Buy2SellBootstrap.taskFacade();
        TaskView task = taskFacade.createTask("Initialize buy2sell");
        System.out.println("Created task: " + task.id() + " - " + task.title());
    }
}
