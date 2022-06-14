package infrastructure.app.run;

import core.task.Task;
import infrastructure.app.config.Configuration;
import infrastructure.util.InvalidCommandException;
import infrastructure.util.CommandParser;

import java.util.Collection;
import java.util.List;

public class App {

    // Test args:
    // add 1 -d:2022-03-01 -c "hello world" -s "PENDING"
    // list
    // remove 1

    public static void main(String[] args) {
        var commandGenerator = Configuration.commandGenerator();

        try {
            var commandDTO = new CommandParser().parse(List.of(args));
            var command = commandGenerator.generate(commandDTO.action());

            Collection<Task> tasks = command.execute(commandDTO);
            System.out.println(commandDTO.action().value());
            System.out.println(tasks);

        }
        catch (InvalidCommandException exception) {
            System.out.println(exception);
            // Todo: print command helper
        }
    }

}
