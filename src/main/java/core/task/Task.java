package core.task;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public record Task(TaskID id, String description, Optional<LocalDate> dueDate,
                   TaskState status, Collection<Task> subtasks) {}
