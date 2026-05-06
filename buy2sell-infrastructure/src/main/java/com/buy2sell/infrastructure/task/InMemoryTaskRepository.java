package com.buy2sell.infrastructure.task;

import com.buy2sell.application.task.TaskRepository;
import com.buy2sell.domain.task.Task;
import com.buy2sell.domain.task.TaskId;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryTaskRepository implements TaskRepository {

    private final Map<TaskId, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public void save(Task task) {
        tasks.put(task.id(), task);
    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }

    public int size() {
        return tasks.size();
    }
}
