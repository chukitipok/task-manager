package infrastructure.repository;

import infrastructure.repository.jsonRepository.JsonTaskEntity;

import java.util.Collection;

public abstract class TaskEntity {

    int id;
    String description;
    String creationDate;
    String dueDate;
    String closeDate;
    int state;
    Collection<JsonTaskEntity> subtasks;

    protected TaskEntity(int id, String description, String creationDate, String dueDate, String closeDate, int state, Collection<JsonTaskEntity> subtasks) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subtasks = subtasks;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public int getState() {
        return state;
    }

    public Collection<JsonTaskEntity> getSubtasks() {
        return subtasks;
    }
}
