package infrastructure.repository;

import infrastructure.repository.models.TaskEntity;

public interface TaskCommands {
    TaskEntity save(TaskEntity entity);
    boolean remove(int id);
}
