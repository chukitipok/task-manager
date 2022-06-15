package core.ports;

import core.task.TaskDto;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

public interface TaskReader {
    Collection<TaskDto> readAll();
    OptionalInt findLastId();
    Optional<TaskDto> findById(int taskId);
}
