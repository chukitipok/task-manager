package infrastructure.app.config;

import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.TaskDto;
import infrastructure.delivery.CommandGenerator;
import infrastructure.adapter.Mapper;
import infrastructure.adapter.TaskMapper;
import infrastructure.adapter.TaskReaderAdapter;
import infrastructure.adapter.TaskWriterAdapter;
import infrastructure.repository.TaskCommands;
import infrastructure.repository.TaskQueries;
import infrastructure.repository.json.JsonTaskCommands;
import infrastructure.repository.json.JsonTaskQueries;
import infrastructure.repository.models.TaskEntity;

public class Configuration {
    private static final String dbPath = "persistence/task_persistence.json";

    private static final TaskQueries taskQueries = new JsonTaskQueries(dbPath);
    private static final TaskCommands taskCommands = new JsonTaskCommands(taskQueries, dbPath);

    private static final Mapper<TaskEntity, TaskDto> taskMapper = new TaskMapper();

    private static final TaskReader taskReader = new TaskReaderAdapter(taskQueries, taskMapper);
    private static final TaskWriter taskWriter = new TaskWriterAdapter(taskCommands, taskMapper);

    private static final CommandGenerator commandGeneratorInstance = new CommandGenerator(taskReader, taskWriter);
    public static CommandGenerator commandGenerator() {
        return commandGeneratorInstance;
    }
    public static String storagePath() {
        return dbPath;
    }

}
