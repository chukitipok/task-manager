package core.ports;

import core.task.TaskDto;

public interface TaskWriter {
    TaskDto save(TaskDto taskToSave);
    boolean remove(int id);
}
