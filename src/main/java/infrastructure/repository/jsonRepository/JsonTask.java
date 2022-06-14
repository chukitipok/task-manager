package infrastructure.repository.jsonRepository;

import org.json.simple.JSONObject;

import java.util.Collection;

public record JsonTask(
        int id,
        String description,
        String creationDate,
        String dueDate,
        String closeDate,
        int state,
        Collection<JsonTask> subtasks
) {
   
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("description", description);
        jsonObject.put("creationDate", creationDate);
        jsonObject.put("dueDate", dueDate);
        jsonObject.put("closeDate", closeDate);
        jsonObject.put("state", state);
        jsonObject.put("subtasks", subtasks.stream().map(task -> task.toJson()));

        return jsonObject;
    }
}
