package com.buy2sell.application.task;

import com.buy2sell.domain.task.Task;
import com.buy2sell.domain.task.TaskId;

import java.util.Optional;

public interface TaskRepository {

    void save(Task task);

    Optional<Task> findById(TaskId taskId);
}
