package infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository<T extends TaskEntity>  {

    TaskEntity save(TaskEntity task);
    Collection<TaskEntity> readAll();
    Optional<Integer> findLastId();

    T taskToRepositoryTask(TaskEntity task);

    int generateId(Collection<TaskEntity> tasks);
}
