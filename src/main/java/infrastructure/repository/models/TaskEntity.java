package infrastructure.repository.models;

import java.util.Collection;

public record TaskEntity (
        int id,
        String description,
        String creationDate,
        String dueDate,
        String closeDate,
        int state,
        Collection<TaskEntity> subtasks
) {}
