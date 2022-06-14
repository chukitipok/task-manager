package core.task;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public record Task(Optional<TaskID> id, String description, Optional<LocalDate> dueDate, TaskState status,
                   Collection<Task> subtasks) {

    public Task setStatus(String newStatus) {
        return new Task(id, description, dueDate, status != null ? TaskState.valueOf(newStatus) : TaskState.TODO, subtasks);
    }
    public Task setDescription(String newDescription) {
        return new Task(id, newDescription, dueDate, status, subtasks);
    }
    public Task setDueDate(String newDueDate) {
        return new Task(id, description, newDueDate != null ? Optional.of(LocalDate.parse(newDueDate)) : Optional.empty(), status, subtasks);
    }
}
