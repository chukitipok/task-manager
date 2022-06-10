package infrastructure.repository;

import java.util.Collection;

public record JsonTask(
        int id,
        String description,
        String creationDate,
        String dueDate,
        String closeDate,
        int state,
        Collection<JsonTask> subtasks
) {}
