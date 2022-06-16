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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

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