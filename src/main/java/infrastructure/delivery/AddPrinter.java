package infrastructure.delivery;

import core.task.Task;

import java.util.Collection;

public class AddPrinter implements Printer {

    public void print(Collection<Task> tasks) {
        var task = tasks.iterator().next();
        System.out.println("id | description | status");
        System.out.println(task.id().value() + " | " + task.description() + " | " + task.status().value());
    }
}
