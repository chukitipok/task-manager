package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import infrastructure.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AddTask implements Command {

    private final TaskRepository taskRepository;

    public AddTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(CommandDTO commandDTO) {
        var dueDateOption = commandDTO.options().get(CommandOption.DUE_DATE);

        Optional<LocalDate> dueDate =
                dueDateOption != null ? Optional.of(LocalDate.parse(dueDateOption)) : Optional.empty();

        var taskId = taskRepository.findLastId().orElseGet(() -> new TaskID(1));

        var task = new Task(
                taskId,
                commandDTO.options().get(CommandOption.CONTENT),
                dueDate,
                TaskState.TODO,
                List.of());

        return taskRepository.save(task);
    }
}
