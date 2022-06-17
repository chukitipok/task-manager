package infrastructure.delivery;
import core.task.Task;
import java.util.Collection;

public interface Printer {
    void print(Collection<Task> tasks);
}
