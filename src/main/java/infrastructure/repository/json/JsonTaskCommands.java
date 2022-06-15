package infrastructure.repository.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.repository.TaskCommands;
import infrastructure.repository.models.TaskEntity;
import infrastructure.repository.TaskQueries;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;

public class JsonTaskCommands implements TaskCommands {

    private final TaskQueries query;
    private final File storage;
    private final ObjectMapper mapper;

    public JsonTaskCommands(TaskQueries queries, String storagePath) {
        query = queries;
        storage = new File(storagePath);
        mapper = new ObjectMapper();
    }

    public TaskEntity save(TaskEntity taskEntity) {
        var taskEntities = query.findAll();
        query.findById(taskEntity.id()).ifPresent(entity -> {
            remove(entity.id());
            taskEntities.remove(entity);
        });

        try (var fileWriter = new FileWriter(storage)) {
            taskEntities.add(taskEntity);

            var json = convert(taskEntities);
            fileWriter.write(json);

            return taskEntity;
        }
        catch (Exception e) {
            System.out.println("error under");
            e.printStackTrace();
            // todo: add well named exception
            throw new RuntimeException(e);
        }
    }

    public boolean remove(int id) {
        var optionalTask = query.findById(id);

        if (optionalTask.isEmpty()) {
            return false;
        }

        var taskEntities = query.findAll();

        try (var fileWriter = new FileWriter(storage)) {

            taskEntities.remove(optionalTask.get());

            var json = convert(taskEntities);
            fileWriter.write(json);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String convert(Collection<TaskEntity> taskEntities) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskEntities);
    }
}
