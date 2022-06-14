package infrastructure.app.config;

import core.usecases.CommandGenerator;
import infrastructure.repository.TaskRepository;
import infrastructure.repository.jsonRepository.JsonTaskRepository;

public class Configuration {

    private static final TaskRepository jsonTaskRepository = new JsonTaskRepository();
    private static final CommandGenerator commandGeneratorInstance = new CommandGenerator(jsonTaskRepository);


    public static TaskRepository taskRepository() {
        return jsonTaskRepository;
    }


    public static CommandGenerator commandGenerator() {
        return commandGeneratorInstance;
    }

    public static final String dbPath = "fileDB/db.json";

}
