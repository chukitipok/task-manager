package infrastructure.delivery;

import core.command.Command;
import core.command.CommandAction;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.usecases.AddTask;
import core.usecases.ListTasks;
import core.usecases.RemoveTask;
import core.usecases.UpdateTask;

public class CommandGenerator {
    private final TaskReader reader;
    private final TaskWriter writer;

    public CommandGenerator(TaskReader reader, TaskWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Command generate(CommandAction action) {
        return switch (action) {
            case ADD -> new AddTask(reader, writer);
            case UPDATE -> new UpdateTask(reader, writer);
            case REMOVE -> new RemoveTask(writer);
            case LIST -> new ListTasks(reader);
        };
    }
}
