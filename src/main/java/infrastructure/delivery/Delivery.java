package infrastructure.delivery;
import core.command.CommandAction;
import core.task.Task;
import infrastructure.util.InvalidCommandException;

import java.util.Collection;


public record Delivery(Collection<Task> tasks, CommandAction type){
    public void display() throws InvalidCommandException {
        Printer printer;
        switch (type) {
            case ADD -> printer = new AddPrinter();
            case LIST -> printer = new ListPrinter();
            case REMOVE -> printer = new RemovePrinter();
//            case UPDATE -> "update";
            default -> throw new InvalidCommandException();
        }

        printer.print(tasks);
    }

}
