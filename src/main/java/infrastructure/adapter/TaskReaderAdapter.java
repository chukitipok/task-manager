package infrastructure.adapter;

import core.ports.TaskReader;
import core.task.TaskDto;
import infrastructure.repository.TaskQueries;
import infrastructure.repository.models.TaskEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

public class TaskReaderAdapter implements TaskReader {

    private final TaskQueries queries;
    private final Mapper<TaskEntity, TaskDto> mapper;

    public TaskReaderAdapter(TaskQueries repository, Mapper<TaskEntity, TaskDto> taskMapper) {
        queries = repository;
        mapper = taskMapper;
    }

    public Collection<TaskDto> readAll() {
        return queries.findAll().stream().map(mapper::toDto).toList();
    }

    public OptionalInt findLastId() {
        return queries.findLastId();
    }

    public Optional<TaskDto> findById(int taskId) {
        return queries.findById(taskId).map(mapper::toDto);
    }
}
