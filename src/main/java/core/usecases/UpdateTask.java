package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.task.Task;
import infrastructure.adapter.TaskToJsonTaskEntity;
import infrastructure.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateTask implements Command {

    private final TaskRepository taskRepository;

    public UpdateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        var dueDateOption = commandDTO.options().get(CommandOption.DUE_DATE);
        var statusOption = commandDTO.options().get(CommandOption.STATUS);
        var contentOption = commandDTO.options().get(CommandOption.CONTENT);

        var toUpdate = taskRepository.findById(commandDTO.taskId());

        var task = TaskToJsonTaskEntity.fromEntity(toUpdate);
        if(contentOption != null) {
            task = task.setDescription(contentOption);
        }
        if(dueDateOption != null) {
            task = task.setDueDate(dueDateOption);
        }
        if(statusOption != null) {
            task = task.setStatus(statusOption);
        }

        var updated = taskRepository.update(TaskToJsonTaskEntity.toEntity(task));

        var result = new ArrayList<Task>();
        result.add(TaskToJsonTaskEntity.fromEntity(updated));
        return result;
    }
}
