package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.task.Task;
import infrastructure.adapter.TaskToJsonTaskEntity;
import infrastructure.repository.TaskEntity;
import infrastructure.repository.TaskRepository;

import java.util.Collection;

public class ListTasks implements Command {

    private final TaskRepository taskRepository;

    public ListTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        Collection<TaskEntity> entities = taskRepository.readAll();
        return entities.stream().map(taskEntity -> TaskToJsonTaskEntity.fromEntity(taskEntity)).toList();
    }
}
