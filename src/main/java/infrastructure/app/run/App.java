package infrastructure.app.run;

import infrastructure.app.config.Configuration;
import infrastructure.delivery.Delivery;
import infrastructure.delivery.HelperPrinter;
import infrastructure.delivery.Printer;
import infrastructure.util.InvalidCommandException;
import infrastructure.util.CommandParser;

import java.util.List;


public class App {

    public static void main(String[] args) {
        var commandGenerator = Configuration.commandGenerator();

        try {
            var commandDTO = new CommandParser().parse(List.of(args));
            var command = commandGenerator.generate(commandDTO.action());
            var tasks = command.execute(commandDTO);

            Delivery delivery = new Delivery(tasks, commandDTO.action());
            delivery.display();
        }
        catch (InvalidCommandException exception) {
            HelperPrinter.print();
        }
    }

}
