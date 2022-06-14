package infrastructure.repository.jsonRepository;

import core.task.Task;
import core.task.TaskID;
import infrastructure.repository.TaskRepository;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonTaskRepository implements TaskRepository<JsonTask> {

    private final String path = "fileDB/db.json";

    @Override
    public Collection<Task> readAll() {
        return null;
    }

    @Override
    public Optional<TaskID> findLastId() {
        return Optional.empty();
    }

    public JsonTask taskToReposistoryTask(Task task) {
        JsonTask jsonTask = new JsonTask(task.id().value(), task.description(), task.dueDate().toString(), task.dueDate().toString(), task.dueDate().toString(), task.status().toValue(),task.subtasks().stream().map(subTask -> taskToReposistoryTask(subTask)).collect(Collectors.toList()));
        return jsonTask;
    }

    @Override
    public Task save(Task task) {

        JsonTask jsonTask = taskToReposistoryTask(task);

        try (FileWriter file = new FileWriter(path)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonTask.toJson().toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return task;
    }
}
