package infrastructure.repository;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository<T extends TaskEntity> {

    TaskEntity save(TaskEntity task);
    void remove(int id);
    TaskEntity update(TaskEntity task);
    TaskEntity findById(int id);
    Collection<TaskEntity> readAll();
    Optional<Integer> findLastId();

    int generateId(Collection<TaskEntity> tasks);
}
