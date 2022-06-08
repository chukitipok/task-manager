package application.core;

import java.time.LocalDateTime;
import java.util.Collection;

public record Task(TaskID id, LocalDateTime creationDate, LocalDateTime closeDate, State state, Collection<Task> subtasks) {}
