package infrastructure.app.run;

import infrastructure.app.config.Configuration;
import infrastructure.util.InvalidCommandException;
import infrastructure.util.CommandParser;

import java.util.List;

public class App {

    public static void main(String[] args) {
        var commandGenerator = Configuration.commandGenerator();

        try {
            var commandDTO = new CommandParser().parse(List.of(args));
            var command = commandGenerator.generate(commandDTO.action());

            var task = command.execute(commandDTO);

            // todo: print task
        }
        catch (InvalidCommandException exception) {
            // Todo: print command helper
        }
    }

}
