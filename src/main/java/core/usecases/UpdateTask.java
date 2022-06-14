package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.task.Task;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import infrastructure.repository.TaskRepository;

public class UpdateTask implements Command {

    private final TaskRepository taskRepository;

    public UpdateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(CommandDTO commandDTO) {
        return null;
    }
}
