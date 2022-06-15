package core.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record TaskDto(
        int id,
        String content,
        Optional<LocalDateTime> dueDate,
        Optional<LocalDateTime> created,
        Optional<LocalDateTime> closed,
        int status,
        List<TaskDto> subtasks) {}
