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

    public JSONObject toJson() {
        JSONArray newSubtasks = new JSONArray();
        subtasks.forEach((task -> newSubtasks.add(task.toJson())));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("description", description);
        jsonObject.put("creationDate", creationDate);
        jsonObject.put("dueDate", dueDate);
        jsonObject.put("closeDate", closeDate);
        jsonObject.put("state", state);
        jsonObject.put("subtasks", subtasks);

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
