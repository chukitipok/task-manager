package infrastructure.repository.jsonRepository;

import infrastructure.app.config.Configuration;
import infrastructure.repository.TaskEntity;
import infrastructure.repository.TaskRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


public class JsonTaskRepository implements TaskRepository<JsonTaskEntity> {

    JSONParser jsonParser = new JSONParser();

    public Collection<TaskEntity> readAll() {
        try (FileReader reader = new FileReader(Configuration.dbPath)) {
            Object obj = jsonParser.parse(reader);
            JSONArray tasksArray = (JSONArray) obj;
            return (Collection<TaskEntity>) tasksArray.stream().map(jsonTask -> JsonTaskEntity.fromJson((JSONObject) jsonTask).toTask()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Integer> findLastId() {
        return Optional.empty();
    }

    public JsonTaskEntity taskToRepositoryTask(TaskEntity task) {
        return new JsonTaskEntity(task.getId(), task.getDescription(), task.getCreationDate(), task.getDueDate(),task.getCloseDate(), task.getState(), task.getSubtasks().stream().map(this::taskToRepositoryTask).collect(Collectors.toList()));
    }

    public int generateId(Collection<TaskEntity> tasks) {
        var maxIdTask = tasks
                .stream()
                .reduce((left, right) -> {
                    int leftId = left.getId();
                    int rightId = right.getId();
                    return leftId > rightId ? left : right;
                })
                .orElse(null);
        return maxIdTask != null
                ? maxIdTask.getId() + 1
                : 1;
    }

    public TaskEntity save(TaskEntity task) {
        var existing = readAll();
        var id = generateId(existing);
        existing.add(new JsonTaskEntity(id, task.getDescription(), LocalDateTime.now().toString(), task.getDueDate(), task.getCloseDate(), task.getState(), task.getSubtasks()));

        JSONArray jsonArray = new JSONArray();
        existing.forEach(toSaveTask -> jsonArray.add((taskToRepositoryTask(toSaveTask).toJson())));

        try (FileWriter file = new FileWriter(Configuration.dbPath)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return task;
    }
}
