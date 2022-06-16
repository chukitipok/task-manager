package infrastructure.delivery;

import core.task.Task;

import java.util.Collection;

public class UpdatePrinter implements Printer {
    public void print(Collection<Task> tasks) {
        var task = tasks.iterator().next();
        System.out.println("Task with id: " + task.id().value() + " has been updated successfully.");
        System.out.println("\tid | description | status");
        System.out.println("\t" + task.id().value() + " | " + task.description() + " | " + task.status().value());
    }
}
