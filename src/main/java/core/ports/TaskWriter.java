package core.ports;

import core.task.Task;

public interface TaskWriter<T> {
    Task save(Task task);
}
