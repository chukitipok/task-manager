package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.task.Task;
import infrastructure.repository.TaskRepository;

import java.util.Collection;

public class RemoveTask implements Command {

    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        taskRepository.remove(commandDTO.taskId().intValue());
        return null;
    }
}
