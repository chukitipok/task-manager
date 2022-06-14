package infrastructure.repository.jsonRepository;

import core.task.Task;
import core.task.TaskID;
import infrastructure.repository.TaskRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonTaskRepository implements TaskRepository<JsonTask> {
    JSONParser jsonParser = new JSONParser();
    private final String path = "fileDB/db.json";

    @Override
    public Collection<Task> readAll() {
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONArray tasksArray = (JSONArray) obj;
            return (Collection<Task>) tasksArray.stream().map(jsonTask -> JsonTask.fromJson((JSONObject) jsonTask).toTask()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<TaskID> findLastId() {
        return Optional.empty();
    }

    @Override
    public JsonTask taskToReposistoryTask(Task task) {
        JsonTask jsonTask = new JsonTask(task.id().get().value(), task.description(), task.dueDate().get().toString(), task.dueDate().get().toString(), task.dueDate().get().toString(), task.status().toValue(), task.subtasks().stream().map(subTask -> taskToReposistoryTask(subTask)).collect(Collectors.toList()));
        return jsonTask;
    }

    @Override
    public int genrateId(Collection<Task> tasks) {
        var maxIdTask = tasks
                .stream()
                .reduce((left, right) -> {
                    int leftId = left.id().get().value();
                    int rightId = right.id().get().value();
                    return leftId > rightId ? left : right;
                })
                .orElse(null);
        return maxIdTask != null
                ? maxIdTask.id().get().value() + 1
                : 0;
    }

    @Override
    public Task save(Task task) {
        var existing = readAll();
        var id = genrateId(existing);
        existing.add(new Task(Optional.of(new TaskID(id)), task.description(), task.dueDate(), task.status(), task.subtasks()));

        JSONArray jsonArray = new JSONArray();
        existing.forEach(toSaveTask -> {
            jsonArray.add((taskToReposistoryTask(toSaveTask).toJson()));
        });

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return task;
    }
}
