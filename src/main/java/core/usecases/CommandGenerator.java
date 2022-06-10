package core.usecases;

import core.command.Command;
import core.command.CommandAction;
import infrastructure.util.InvalidCommandException;
import core.ports.TaskReader;
import core.ports.TaskWriter;

public class CommandGenerator {

    private final TaskReader taskReader;
    private final TaskWriter taskWriter;

    public CommandGenerator(TaskReader taskReader, TaskWriter taskWriter) {
        this.taskReader = taskReader;
        this.taskWriter = taskWriter;
    }

    public Command generate(CommandAction action) {
        return switch (action) {
            case ADD -> new AddTask(taskReader, taskWriter);
            case UPDATE -> new UpdateTask(taskReader, taskWriter);
            case REMOVE -> new RemoveTask(taskReader, taskWriter);
            case LIST -> new ListTasks(taskReader);
        };
    }
}
