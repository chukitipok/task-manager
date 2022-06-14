package core.ports;

import core.task.Task;
import core.task.TaskID;
import infrastructure.repository.TaskEntity;

import java.util.Collection;
import java.util.Optional;

public interface TaskReader {
    Collection<TaskEntity> readAll();
    Optional<Integer> findLastId();
}
