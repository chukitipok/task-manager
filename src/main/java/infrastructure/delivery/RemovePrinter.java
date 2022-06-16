package infrastructure.delivery;

import core.task.Task;

import java.util.Collection;

public class RemovePrinter implements Printer {
    public void print(Collection<Task> tasks) {
        var task = tasks.iterator().next();
        System.out.println("task with id: " + task.id().value());
    }
}
