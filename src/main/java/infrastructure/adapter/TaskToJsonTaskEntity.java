package infrastructure.adapter;

import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import infrastructure.repository.TaskEntity;
import infrastructure.repository.jsonRepository.JsonTaskEntity;

import java.time.LocalDate;
import java.util.Optional;

public class TaskToJsonTaskEntity {
    public static JsonTaskEntity toEntity(Task task) {
        return new JsonTaskEntity(task.id().orElseGet(() -> new TaskID(-1)).value(), task.description(), null, task.dueDate().get().toString(),  null, task.status().toValue(), task.subtasks().stream().map(subtask -> toEntity(subtask)).toList());
    }

    public static Task fromEntity(TaskEntity task) {
        return new Task(
                Optional.of(new TaskID(task.getId())),
                task.getDescription(),
                Optional.of(LocalDate.parse(task.getDueDate())),
                TaskState.values()[task.getState()],
                task.getSubtasks().stream().map(subtask -> fromEntity(subtask)).toList());
    }
}
