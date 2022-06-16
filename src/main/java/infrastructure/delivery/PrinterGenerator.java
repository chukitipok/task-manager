package infrastructure.delivery;

import core.command.CommandAction;

public class PrinterGenerator {

    public Printer generate(CommandAction action) {
        return switch (action) {
            case ADD -> new AddPrinter();
            case LIST ->  new ListPrinter();
            case REMOVE -> new RemovePrinter();
            case UPDATE -> new UpdatePrinter();
        };
    }
}
