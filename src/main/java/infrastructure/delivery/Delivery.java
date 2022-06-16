package infrastructure.delivery;
import core.command.CommandAction;
import core.task.Task;

import java.util.Collection;

public class Delivery {
    private final PrinterGenerator generator;

    public Delivery(){
        generator = new PrinterGenerator();
    }

    public void display(CommandAction action, Collection<Task> tasks) {
        generator.generate(action).print(tasks);
    }
}
