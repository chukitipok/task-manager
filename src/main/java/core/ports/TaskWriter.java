package core.ports;

import core.task.Task;

public interface TaskWriter {
    Task save(Task task);
}
