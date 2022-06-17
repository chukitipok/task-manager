package infrastructure.adapter;

import core.ports.TaskWriter;
import core.task.TaskDto;
import infrastructure.repository.TaskCommands;
import infrastructure.repository.models.TaskEntity;

public class TaskWriterAdapter implements TaskWriter {

    private final TaskCommands commands;
    private final Mapper<TaskEntity, TaskDto> mapper;

    public TaskWriterAdapter(TaskCommands taskCommands, Mapper<TaskEntity, TaskDto> taskMapper) {
        commands = taskCommands;
        mapper = taskMapper;
    }

    public TaskDto save(TaskDto taskToSave) {
        return mapper.toDto(commands.save(mapper.toEntity(taskToSave)));
    }

    public boolean remove(int id) {
        return commands.remove(id);
    }
}
