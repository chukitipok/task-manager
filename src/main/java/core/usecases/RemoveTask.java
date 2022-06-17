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
        if(commandDTO.taskId() == null) {
            throw new IllegalArgumentException();
        }
        writer.remove(commandDTO.taskId());
        return null;
    }
}
