package infrastructure.app.run;

import core.command.CommandAction;
import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import infrastructure.app.config.Configuration;
import infrastructure.delivery.Delivery;
import infrastructure.util.InvalidCommandException;
import infrastructure.util.CommandParser;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        var commandGenerator = Configuration.commandGenerator();

        try {
            var commandDTO = new CommandParser().parse(List.of(args));
            var command = commandGenerator.generate(commandDTO.action());
            var task = command.execute(commandDTO);

            List subtasks = Collections.EMPTY_LIST;
            Task task1 = new Task(new TaskID(1), "hello word", Optional.of(LocalDate.now()), TaskState.PROGRESS, subtasks);
            Delivery delivery = new Delivery(task1, CommandAction.ADD);
            delivery.display();
        }
        catch (InvalidCommandException exception) {
            Delivery.displayCommandHelper();
        }
    }

}
