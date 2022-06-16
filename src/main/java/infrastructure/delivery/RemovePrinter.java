package infrastructure.delivery;

import core.task.Task;

import java.util.Collection;

public class RemovePrinter implements Printer {
    public void print(Collection<Task> tasks) {
        System.out.println("Task has been successfully removed.");
    }
}
