package core.command;

import core.task.Task;
import infrastructure.util.InvalidCommandException;

import java.util.Collection;

public interface Command {
    Collection<Task> execute(CommandDTO commandDTO) throws InvalidCommandException;
}
