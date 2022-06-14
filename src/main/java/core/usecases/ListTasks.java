package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.task.Task;
import core.ports.TaskReader;
import infrastructure.repository.TaskRepository;

import java.util.Collection;

public class ListTasks implements Command {

    private final TaskRepository taskRepository;

    public ListTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        return taskRepository.readAll();
    }
}
