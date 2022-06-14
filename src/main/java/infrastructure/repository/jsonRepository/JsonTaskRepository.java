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
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


public class JsonTaskRepository implements TaskRepository<JsonTaskEntity> {

    JSONParser jsonParser = new JSONParser();

    public Collection<TaskEntity> readAll() {
        try (FileReader reader = new FileReader(Configuration.dbPath)) {
            Object obj = jsonParser.parse(reader);
            JSONArray tasksArray = (JSONArray) obj;
            return (Collection<TaskEntity>) tasksArray.stream().map(jsonTask -> JsonTaskEntity.fromJson((JSONObject) jsonTask)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaskEntity findById(int id) {
        return readAll().stream().filter(taskEntity -> taskEntity.getId() == id).findFirst().get();
    }

    public Optional<Integer> findLastId() {
        var tasks = readAll();
        if(tasks.size() == 0) return Optional.empty();
        var maxIdTask = tasks
                .stream()
                .reduce((left, right) -> {
                    int leftId = left.getId();
                    int rightId = right.getId();
                    return leftId > rightId ? left : right;
                })
                .orElse(null);
        return Optional.of(maxIdTask.getId());
    }

    /*
    public JsonTaskEntity taskToRepositoryTask(TaskEntity task) {
        return new JsonTaskEntity(task.getId(), task.getDescription(), task.getCreationDate(), task.getDueDate(),task.getCloseDate(), task.getState(), task.getSubtasks().stream().map(this::taskToRepositoryTask).collect(Collectors.toList()));
    }
    */

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
        var id = task.getId() == -1 ? generateId(existing) : task.getId();
        existing.add(new JsonTaskEntity(id, task.getDescription(), LocalDate.now().toString(), task.getDueDate(), task.getCloseDate(), task.getState(), task.getSubtasks()));

        JSONArray jsonArray = new JSONArray();
        existing.forEach(toSaveTask -> jsonArray.add(JsonTaskEntity.toJson(toSaveTask)));

        try (FileWriter file = new FileWriter(Configuration.dbPath)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return task;
    }

    @Override
    public void remove(int id) {
        var removed = readAll().stream().filter(task -> task.getId() != id).toList();

        JSONArray jsonArray = new JSONArray();
        removed.forEach(toSaveTask -> jsonArray.add(JsonTaskEntity.toJson(toSaveTask)));

        try (FileWriter file = new FileWriter(Configuration.dbPath)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskEntity update(TaskEntity taskEntity) {
        var dbTask = findById(taskEntity.getId());
        dbTask.setDescription(taskEntity.getDescription());
        dbTask.setCreationDate(taskEntity.getCreationDate());
        dbTask.setDueDate(taskEntity.getDueDate());
        dbTask.setCloseDate(taskEntity.getCloseDate());
        dbTask.setState(taskEntity.getState());
        dbTask.setSubtasks(taskEntity.getSubtasks());

        var updated = readAll().stream().filter(task -> task.getId() != dbTask.getId()).collect(Collectors.toList());
        updated.add(dbTask);

        JSONArray jsonArray = new JSONArray();
        updated.forEach(toSaveTask -> jsonArray.add(JsonTaskEntity.toJson(toSaveTask)));

        try (FileWriter file = new FileWriter(Configuration.dbPath)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbTask;
    }
}
