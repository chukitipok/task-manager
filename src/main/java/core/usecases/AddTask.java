package core.usecases;

import core.command.Command;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class AddTask implements Command {

    private final TaskReader reader;
    private final TaskWriter writer;

    public AddTask(TaskReader reader, TaskWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Collection<Task> execute(CommandDTO commandDTO) {
        var dueDateOption = commandDTO.options().get(CommandOption.DUE_DATE);
        var content = commandDTO.options().get(CommandOption.CONTENT);
        var dueDate = Optional.ofNullable(dueDateOption);

        var task = Task.create(reader.findLastId(), content, dueDate);

        writer.save(task.createDto());

        var tasks = new ArrayList<Task>();
        tasks.add(task);

        return tasks;
    }


}
