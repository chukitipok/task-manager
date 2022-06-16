package infrastructure.delivery;

import core.task.Task;

import java.util.Collection;

public class AddPrinter implements Printer {
    public void print(Collection<Task> tasks) {
        var task = tasks.iterator().next();
        String dueDate = "not assigned";
        if (task.dueDate().isPresent()) {
            dueDate = task.dueDate().get().toLocalDate().toString();
        }
        System.out.println("Task has been successfully added.");
        System.out.println("\tid | description | status | due date");
        System.out.println("\t------------------------------------");
        System.out.println("\t" + task.id().value() + " | " + task.description() + " | " + task.status().value() +
                " | " + dueDate);
    }
}
