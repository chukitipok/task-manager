package core.ports;

import core.task.Task;
import infrastructure.repository.TaskEntity;

public interface TaskWriter<T> {
    TaskEntity save(TaskEntity task);
    void remove(int id);
    TaskEntity update(TaskEntity task);
}
