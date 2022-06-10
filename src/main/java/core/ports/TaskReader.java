package core.ports;

import core.task.Task;
import core.task.TaskID;

import java.util.Collection;
import java.util.Optional;

public interface TaskReader {
    Collection<Task> readAll();
    Optional<TaskID> findLastId();
}
