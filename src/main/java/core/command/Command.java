package core.command;

import core.task.Task;

public interface Command {
    Task execute(CommandDTO commandDTO);
}
