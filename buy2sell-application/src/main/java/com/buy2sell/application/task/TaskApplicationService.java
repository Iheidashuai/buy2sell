package com.buy2sell.application.task;

import com.buy2sell.domain.task.Task;
import com.buy2sell.domain.task.TaskId;
import com.buy2sell.domain.task.TaskTitle;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

public final class TaskApplicationService {

    private final TaskRepository taskRepository;
    private final Clock clock;

    public TaskApplicationService(TaskRepository taskRepository, Clock clock) {
        this.taskRepository = Objects.requireNonNull(taskRepository, "taskRepository must not be null");
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
    }

    public TaskView create(CreateTaskCommand command) {
        Task task = Task.create(TaskId.newId(), TaskTitle.of(command.title()), now());
        taskRepository.save(task);
        return TaskView.from(task);
    }

    public TaskView rename(RenameTaskCommand command) {
        Task task = getTask(command.taskId());
        task.rename(TaskTitle.of(command.newTitle()));
        taskRepository.save(task);
        return TaskView.from(task);
    }

    public TaskView complete(CompleteTaskCommand command) {
        Task task = getTask(command.taskId());
        task.complete(now());
        taskRepository.save(task);
        return TaskView.from(task);
    }

    public TaskView get(String taskId) {
        return TaskView.from(getTask(taskId));
    }

    private Task getTask(String taskId) {
        return taskRepository.findById(TaskId.of(taskId))
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private Instant now() {
        return Instant.now(clock);
    }
}
