package core.usecases;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListTasksTest {

    @Mock
    private TaskReader taskReader;

    @InjectMocks
    private ListTasks sut;

    @Test
    public void should_return_empty_list() {
        var input = new CommandDTO(CommandAction.LIST, null, new HashMap<>());
        when(taskReader.readAll()).thenReturn(List.of());

        var result = sut.execute(input);
        assertTrue(result.isEmpty());

        verify(taskReader).readAll();
        verifyNoMoreInteractions(taskReader);
    }

    @Test
    public void should_return_non_empty_list() {
        var date = LocalDateTime.now();
        var input = new CommandDTO(CommandAction.LIST, null, new HashMap<>());

        var task = new Task(
                new TaskID(1),
                input.options().get(CommandOption.CONTENT),
                Optional.of(date),
                TaskState.TODO,
                List.of());

        when(taskReader.readAll()).thenReturn(List.of(task.createDto()));

        var result = sut.execute(input);
        assertFalse(result.isEmpty());

        verify(taskReader).readAll();
        verifyNoMoreInteractions(taskReader);
    }
}