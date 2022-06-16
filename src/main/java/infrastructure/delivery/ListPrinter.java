package infrastructure.delivery;
import core.task.Task;

import java.util.Collection;
import java.util.Comparator;

public class ListPrinter implements Printer {
    public void print(Collection<Task> tasks) {
        System.out.println("id | description | status | due date");
        System.out.println("------------------------------------");
        tasks.stream()
                .sorted(Comparator.comparing(a -> a.id().value()))
                .forEach(task -> {
                    String dueDate = "not assigned";
                    if (task.dueDate().isPresent()) {
                        dueDate = task.dueDate().get().toLocalDate().toString();
                    }
                    System.out.println(task.id().value() + " | "
                                    + task.description() + " | "
                                    + task.status().value() + " | "
                                    + dueDate
                    );
                });
    }
}
