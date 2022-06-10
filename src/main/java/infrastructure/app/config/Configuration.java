package infrastructure.app.config;

import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.usecases.CommandGenerator;
import infrastructure.repository.JsonTaskReader;
import infrastructure.repository.JsonTaskWriter;

public class Configuration {

    private static final TaskReader taskReaderInstance = new JsonTaskReader();
    private static final TaskWriter taskWriterInstance = new JsonTaskWriter();
    private static final CommandGenerator commandGeneratorInstance = new CommandGenerator(taskReader(), taskWriter());


    public static TaskReader taskReader() {
        return taskReaderInstance;
    }

    public static TaskWriter taskWriter() {
        return taskWriterInstance;
    }

    public static CommandGenerator commandGenerator() {
        return commandGeneratorInstance;
    }
}
