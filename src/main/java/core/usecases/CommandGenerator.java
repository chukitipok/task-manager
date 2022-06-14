package core.usecases;

import core.command.Command;
import core.command.CommandAction;
import infrastructure.repository.TaskRepository;
import infrastructure.util.InvalidCommandException;
import core.ports.TaskReader;
import core.ports.TaskWriter;

public class CommandGenerator {

    private final TaskRepository taskRepository;

    public CommandGenerator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Command generate(CommandAction action) {
        return switch (action) {
            case ADD -> new AddTask(taskRepository);
            case UPDATE -> new UpdateTask(taskRepository);
            case REMOVE -> new RemoveTask(taskRepository);
            case LIST -> new ListTasks(taskRepository);
        };
    }
}
