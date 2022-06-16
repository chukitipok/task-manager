package core.usecases;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.ports.TaskReader;
import core.ports.TaskWriter;
import infrastructure.util.InvalidCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveTaskTest {

    @Mock
    private TaskReader taskReader;

    @Mock
    private TaskWriter taskWriter;

    @InjectMocks
    private RemoveTask sut;

    @Test
    void should_throw_error_when_taskId_is_missing() {
        var input = new CommandDTO(CommandAction.REMOVE, null, new HashMap());
        assertThrowsExactly(IllegalArgumentException.class, () -> sut.execute(input));
    }

    @Test
    void should_remove_task() throws InvalidCommandException {
        var input = new CommandDTO(CommandAction.REMOVE, 1, new HashMap());

        when(taskWriter.remove(any(Integer.class))).thenReturn(true);

        Null expected = null;
        var result = sut.execute(input);
        assertEquals(expected, result);

        verify(taskWriter).remove(any(Integer.class));
        verifyNoMoreInteractions(taskWriter);
        verifyNoMoreInteractions(taskReader);
    }
}