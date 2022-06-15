package infrastructure.repository;

import infrastructure.repository.models.TaskEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

public interface TaskQueries {
    Optional<TaskEntity> findById(int id);
    Collection<TaskEntity> findAll();
    OptionalInt findLastId();
}
