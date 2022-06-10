package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.task.Task;
import core.ports.TaskReader;
import core.ports.TaskWriter;

public class RemoveTask implements Command {

    private final TaskReader taskReader;
    private final TaskWriter taskWriter;

    public RemoveTask(TaskReader taskReader, TaskWriter taskWriter) {
        this.taskReader = taskReader;
        this.taskWriter = taskWriter;
    }

    public Task execute(CommandDTO commandDTO) {
        return null;
    }
}
