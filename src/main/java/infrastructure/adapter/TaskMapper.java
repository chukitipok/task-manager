package infrastructure.adapter;

import core.task.TaskDto;
import infrastructure.repository.models.TaskEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class TaskMapper implements Mapper<TaskEntity, TaskDto> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TaskDto toDto(TaskEntity entity) {
        var subtasks = entity.subtasks().stream().map(this::toDto).toList();

        return new TaskDto(
                entity.id(),
                entity.description(),
                getDateTimeFrom(entity.dueDate()),
                getDateTimeFrom(entity.creationDate()),
                getDateTimeFrom(entity.closeDate()),
                entity.state(),
                subtasks);
    }

    public TaskEntity toEntity(TaskDto dto) {
        var created = dto.created().isPresent() ? dto.created().get().format(formatter) : null;
        var dueDate = dto.dueDate().isPresent() ? dto.dueDate().get().format(formatter) : null;
        var closeDate = dto.closed().isPresent() ? dto.closed().get().format(formatter) : null;

        var subtasks = dto.subtasks().stream().map(this::toEntity).toList();

        return new TaskEntity(
                dto.id(),
                dto.content(),
                created,
                dueDate,
                closeDate,
                dto.status(),
                subtasks);
    }

    private Optional<LocalDateTime> getDateTimeFrom(String dateTimeToParse) {
        try {
            var dateTime = LocalDateTime.parse(dateTimeToParse, formatter);
            return Optional.of(dateTime);
        }
        catch (DateTimeParseException | NullPointerException exception) {
            return Optional.empty();
        }
    }
}
