package infrastructure.repository;

import core.task.Task;
import core.task.TaskID;
import core.ports.TaskReader;

import java.util.Collection;
import java.util.Optional;

public class JsonTaskReader implements TaskReader {


    public Collection<Task> readAll() {
        return null;
    }
    public Optional<TaskID> findLastId() {
        return Optional.empty();
    }
}
