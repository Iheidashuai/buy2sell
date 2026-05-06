package com.buy2sell.adapter.task;

import com.buy2sell.application.task.CompleteTaskCommand;
import com.buy2sell.application.task.CreateTaskCommand;
import com.buy2sell.application.task.RenameTaskCommand;
import com.buy2sell.application.task.TaskApplicationService;
import com.buy2sell.application.task.TaskView;

import java.util.Objects;

public final class TaskFacade {

    private final TaskApplicationService taskApplicationService;

    public TaskFacade(TaskApplicationService taskApplicationService) {
        this.taskApplicationService = Objects.requireNonNull(taskApplicationService, "taskApplicationService must not be null");
    }

    public TaskView createTask(String title) {
        return taskApplicationService.create(new CreateTaskCommand(title));
    }

    public TaskView renameTask(String taskId, String newTitle) {
        return taskApplicationService.rename(new RenameTaskCommand(taskId, newTitle));
    }

    public TaskView completeTask(String taskId) {
        return taskApplicationService.complete(new CompleteTaskCommand(taskId));
    }

    public TaskView getTask(String taskId) {
        return taskApplicationService.get(taskId);
    }
}
