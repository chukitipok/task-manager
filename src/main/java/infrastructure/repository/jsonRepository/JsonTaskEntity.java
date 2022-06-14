package infrastructure.repository.jsonRepository;

import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import infrastructure.repository.TaskEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class JsonTaskEntity extends TaskEntity {

    int id;
    String description;
    String creationDate;
    String dueDate;
    String closeDate;
    int state;
    Collection<JsonTaskEntity> subtasks;

    public JsonTaskEntity(int id, String description, String creationDate, String dueDate, String closeDate, int state, Collection<JsonTaskEntity> subtasks) {
        super(id, description, creationDate, dueDate, closeDate, state, subtasks);
    }

    public static JSONObject toJson(TaskEntity taskEntity) {
        JSONArray newSubtasks = new JSONArray();
        taskEntity.getSubtasks().forEach((task -> newSubtasks.add(JsonTaskEntity.toJson(task))));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", taskEntity.getId());
        jsonObject.put("description", taskEntity.getDescription());
        jsonObject.put("creationDate", taskEntity.getCreationDate());
        jsonObject.put("dueDate", taskEntity.getDueDate());
        jsonObject.put("closeDate", taskEntity.getCloseDate());
        jsonObject.put("state", taskEntity.getState());
        jsonObject.put("subtasks", taskEntity.getSubtasks());

        return jsonObject;
    }

    public Task toTask() {
        return new Task(Optional.of(new TaskID(id)), description, Optional.of(LocalDate.parse(dueDate)), TaskState.values()[state], subtasks.stream().map(subtask -> subtask.toTask()).toList());
    }

    public static JsonTaskEntity fromJson(JSONObject jsonTask) {
        return new JsonTaskEntity(
                ((Long) jsonTask.get("id")).intValue(),
                (String) jsonTask.get("description"),
                (String) jsonTask.get("dueDate"),
                (String) jsonTask.get("dueDate"),
                (String) jsonTask.get("dueDate"),
                ((Long) jsonTask.get("state")).intValue(),
                (Collection<JsonTaskEntity>) ((JSONArray) jsonTask.get("subtasks")).stream().map(subTask -> fromJson((JSONObject) subTask)).toList());
    }
}
