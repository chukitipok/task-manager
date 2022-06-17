package infrastructure.repository.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import infrastructure.repository.models.TaskEntity;
import infrastructure.repository.TaskQueries;

import java.io.File;
import java.util.*;

public class JsonTaskQueries implements TaskQueries {

    private final String storagePath;
    private final ObjectMapper mapper;

    public JsonTaskQueries(String path) {
        storagePath = path;
        mapper = new ObjectMapper();
    }

    public Optional<TaskEntity> findById(int id) {
        return findAll()
                .stream()
                .filter(entity -> entity.id() == id)
                .findFirst();
    }

    public Collection<TaskEntity> findAll() {
        try {
            var json = Files.asCharSource(new File(storagePath), Charsets.UTF_8).read();
            var targetType = mapper.getTypeFactory().constructCollectionType(List.class, TaskEntity.class);

            return mapper.readValue(json, targetType);
        }
        catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public OptionalInt findLastId() {
        return findAll()
                .stream()
                .mapToInt(TaskEntity::id)
                .max();
    }
}
