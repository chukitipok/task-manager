package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.task.Task;
import core.ports.TaskReader;

public class ListTasks implements Command {

    private final TaskReader taskReader;

    public ListTasks(TaskReader taskReader) {
        this.taskReader = taskReader;
    }

    public Task execute(CommandDTO commandDTO) {
        return null;
    }
}
