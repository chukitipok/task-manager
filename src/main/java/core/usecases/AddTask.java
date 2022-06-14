package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.task.Task;
import core.task.TaskState;
import infrastructure.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AddTask implements Command {

    private final TaskRepository taskRepository;

    public AddTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        var dueDateOption = commandDTO.options().get(CommandOption.DUE_DATE);
        var statusOption = commandDTO.options().get(CommandOption.STATUS);

        Optional<LocalDate> dueDate =
                dueDateOption != null ? Optional.of(LocalDate.parse(dueDateOption)) : Optional.empty();

        var task = new Task(
                null,
                commandDTO.options().get(CommandOption.CONTENT),
                dueDate,
                statusOption != null ? TaskState.valueOf(statusOption) : TaskState.TODO,
                List.of());

        var created = taskRepository.save(task);
        var tasks = new ArrayList<Task>();
        tasks.add(created);
        return tasks;
    }
}
