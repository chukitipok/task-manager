package infrastructure.repository;

import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;

import java.util.Collection;

public interface TaskRepository<T> extends TaskReader, TaskWriter {
    T taskToReposistoryTask(Task task);
    int genrateId(Collection<Task> tasks);
}
