package core.command;

import core.task.Task;

import java.util.Collection;

public interface Command {
    Collection<Task> execute(CommandDTO commandDTO);
}
