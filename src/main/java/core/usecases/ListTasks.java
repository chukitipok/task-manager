package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.ports.TaskReader;
import core.task.Task;

import java.util.Collection;

public class ListTasks implements Command {

    private final TaskReader reader;

    public ListTasks(TaskReader reader) {
        this.reader = reader;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        return reader.readAll().stream().map(Task::new).toList();
    }
}
