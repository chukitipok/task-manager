package core.task;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public record Task(
        TaskID id,
        String description,
        Optional<LocalDateTime> dueDate,
        TaskState status,
        Collection<TaskDto> subtasks)
{
    public Task(TaskDto dto) {
        this(
          new TaskID(dto.id()),
          dto.content(),
          dto.dueDate(),
          TaskState.values()[dto.status()],
          dto.subtasks()
        );
    }

    public static Task create(OptionalInt id, String content, Optional<String> dueDate) {
        return new Task(
                new TaskID(generateTaskID(id)),
                content,
                dueDate.map(LocalDateTime::parse),
                TaskState.TODO,
                List.of());
    }

    public static TaskDto update(TaskDto dto, String content, Optional<String> dueDate, String status) {
        var description = content != null ? content : dto.content();
        var state = status != null ? TaskState.valueOf(status).toValue() : dto.status();
        var date = dueDate.isPresent() ? dueDate.map(LocalDateTime::parse) : dto.dueDate();

        return new TaskDto(
                dto.id(),
                description,
                date,
                dto.created(),
                dto.closed(),
                state,
                dto.subtasks());
    }
    public TaskDto createDto() {
        return new TaskDto(
                id.value(),
                description,
                dueDate,
                Optional.of(LocalDateTime.now()),
                Optional.empty(),
                status.toValue(),
                subtasks.stream().toList()
        );
    }

    public Task setStatus(String newStatus) {
        return new Task(id, description, dueDate, status != null ? TaskState.valueOf(newStatus) : TaskState.TODO, subtasks);
    }
    public Task setDescription(String newDescription) {
        return new Task(id, newDescription, dueDate, status, subtasks);
    }
    public Task setDueDate(String newDueDate) {
        return new Task(id, description, newDueDate != null ? Optional.of(LocalDateTime.parse(newDueDate)) : Optional.empty(), status, subtasks);
    }

    private static int generateTaskID(OptionalInt optionalLastId) {
        if (optionalLastId.isPresent()) {
            return optionalLastId.getAsInt() + 1;
        }

        return 1;
    }
}
