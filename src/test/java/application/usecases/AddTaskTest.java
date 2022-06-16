package application.usecases;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;
import core.task.TaskDto;
import core.task.TaskID;
import core.task.TaskState;
import core.usecases.AddTask;
import infrastructure.util.InvalidCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddTaskTest {

    @Mock
    private TaskReader taskReader;

    @Mock
    private TaskWriter taskWriter;

    @InjectMocks
    private AddTask sut;

    @Test
    void should_throw_error_when_task_is_invalid() {
        var options = new HashMap<CommandOption, String>();
        options.put(CommandOption.CONTENT, "");
        var input = new CommandDTO(CommandAction.ADD, null, options);

        when(taskReader.findLastId()).thenReturn(OptionalInt.empty());

        assertThrowsExactly(InvalidCommandException.class, () -> sut.execute(input));

        verify(taskReader).findLastId();
        verifyNoMoreInteractions(taskReader);
        verifyNoInteractions(taskWriter);
    }

    @Test
    void should_add_a_new_task() throws InvalidCommandException {
        var date = LocalDateTime.now();
        var options = new HashMap<CommandOption, String>();
        options.put(CommandOption.CONTENT, "This a test which should work");
        options.put(CommandOption.DUE_DATE, date.toString());
        options.put(CommandOption.STATUS, "PENDING");

        var input = new CommandDTO(CommandAction.ADD, null, options);

        var expected = new Task(
                new TaskID(1),
                input.options().get(CommandOption.CONTENT),
                Optional.of(date),
                TaskState.TODO,
                List.of());

        when(taskReader.findLastId()).thenReturn(OptionalInt.empty());
        when(taskWriter.save(any(TaskDto.class))).thenReturn(expected.createDto());

        var result = sut.execute(input);
        assertEquals(expected, result.toArray()[0]);

        verify(taskReader).findLastId();
        verifyNoMoreInteractions(taskReader);

        verify(taskWriter).save(any(TaskDto.class));
        verifyNoMoreInteractions(taskWriter);
    }
}