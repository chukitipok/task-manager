package core.usecases;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;
import core.task.TaskDto;
import core.task.TaskID;
import core.task.TaskState;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateTaskTest {

    @Mock
    private TaskReader reader;

    @Mock
    private TaskWriter writer;

    @InjectMocks
    private UpdateTask sut;

    @Test
    void should_update_a_task() throws InvalidCommandException {
        var date = LocalDateTime.now();
        var options = new HashMap<CommandOption, String>();
        options.put(CommandOption.CONTENT, "updated");
        options.put(CommandOption.DUE_DATE, date.toString());
        options.put(CommandOption.STATUS, "DONE");

        var input = new CommandDTO(CommandAction.UPDATE, 1, options);

        var current = new Task(
                new TaskID(1),
                input.options().get(CommandOption.CONTENT),
                Optional.of(LocalDateTime.now()),
                TaskState.TODO,
                List.of());

        var expected = new Task(
                new TaskID(1),
                "updated",
                Optional.of(date),
                TaskState.DONE,
                List.of());

        when(reader.findById(any(Integer.class))).thenReturn(Optional.of(current.createDto()));
        when(writer.save(any(TaskDto.class))).thenReturn(expected.createDto());

        var result = sut.execute(input);
        assertEquals(expected, result.toArray()[0]);

        verifyNoMoreInteractions(reader);

        verify(writer).save(any(TaskDto.class));
        verifyNoMoreInteractions(writer);
    }

    @Test
    public void should_throw_error_when_task_does_not_exist() {
        var input = new CommandDTO(CommandAction.UPDATE, 0, new HashMap<>());

        when(reader.findById(anyInt())).thenReturn(Optional.empty());

        assertThrowsExactly(InvalidCommandException.class, () -> sut.execute(input));

        verify(reader).findById(anyInt());
        verifyNoMoreInteractions(reader);
        verifyNoInteractions(writer);
    }

    @Test
    public void should_throw_error_when_task_id_is_null() {
        var input = new CommandDTO(CommandAction.UPDATE, null, new HashMap<>());
        assertThrowsExactly(InvalidCommandException.class, () -> sut.execute(input));
        verifyNoInteractions(reader, writer);
    }
}