package application.usecases;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import core.usecases.AddTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddTaskTest {

    @Mock
    private TaskReader mockedTaskReader;

    @Mock
    private TaskWriter mockedTaskWriter;

    @InjectMocks
    private AddTask sut;

    @Test
    void should_add_a_new_task() {
        var input = new CommandDTO(CommandAction.ADD, null, new HashMap<>());

        var expected = new Task(
                new TaskID(1),
                input.options().get(CommandOption.CONTENT),
                Optional.empty(),
                TaskState.TODO,
                List.of());

        when(mockedTaskReader.findLastId()).thenReturn(Optional.empty());
        when(mockedTaskWriter.save(any(Task.class))).thenReturn(expected);

        var result = sut.execute(input);
        assertEquals(expected, result);

        verify(mockedTaskReader).findLastId();
        verify(mockedTaskWriter).save(any(Task.class));
        verifyNoMoreInteractions(mockedTaskWriter);
    }
}