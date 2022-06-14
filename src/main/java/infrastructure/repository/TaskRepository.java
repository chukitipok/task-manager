package infrastructure.repository;

import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;

public interface TaskRepository<T> extends TaskReader, TaskWriter {
    T taskToReposistoryTask(Task task);
}
