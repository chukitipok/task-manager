package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.ports.TaskWriter;
import core.task.Task;

import java.util.Collection;

public class RemoveTask implements Command {

    private final TaskWriter writer;

    public RemoveTask(TaskWriter taskWriter) {
        writer = taskWriter;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        writer.remove(commandDTO.taskId());
        return null;
    }
}
