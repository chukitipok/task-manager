package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;
import core.task.TaskDto;
import core.task.TaskState;
import infrastructure.util.InvalidCommandException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateTask implements Command {

    private final TaskReader reader;
    private final TaskWriter writer;

    public UpdateTask(TaskReader reader, TaskWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Collection<Task> execute(CommandDTO commandDTO) throws InvalidCommandException {
        try {
            var dueDateOption = commandDTO.options().get(CommandOption.DUE_DATE);
            var statusOption = commandDTO.options().get(CommandOption.STATUS);
            var contentOption = commandDTO.options().get(CommandOption.CONTENT);
            var dueDate = Optional.ofNullable(dueDateOption);

            var dto = reader.findById(commandDTO.taskId()).orElseThrow();
            var task = Task.update(dto, contentOption, dueDate, statusOption);

            var closeDate = task.status() == TaskState.DONE.toValue() ? Optional.of(LocalDateTime.now()) : dto.closed();

            var updated = new TaskDto(
                    task.id(),
                    task.content(),
                    task.dueDate(),
                    task.created(),
                    closeDate,
                    task.status(),
                    task.subtasks());

            writer.save(updated);
            return List.of(new Task(task));
        }
        catch (NoSuchElementException | NullPointerException ignored) {
            throw new InvalidCommandException();
        }
    }
}
