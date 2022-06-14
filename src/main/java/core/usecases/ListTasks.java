package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.task.Task;
import core.ports.TaskReader;
import infrastructure.repository.TaskRepository;

public class ListTasks implements Command {

    private final TaskRepository taskRepository;

    public ListTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(CommandDTO commandDTO) {
        return null;
    }
}
