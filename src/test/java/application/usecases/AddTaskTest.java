package application.usecases;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.command.CommandOption;
import core.task.Task;
import core.task.TaskID;
import core.task.TaskState;
import core.usecases.AddTask;
import infrastructure.adapter.TaskToJsonTaskEntity;
import infrastructure.repository.TaskEntity;
import infrastructure.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddTaskTest {

    @Mock
    private TaskRepository mockedTaskRepository;

    @InjectMocks
    private AddTask sut;

    @Test
    void should_add_a_new_task() {
        var date = LocalDate.now();
        var options = new HashMap<CommandOption, String>();
        options.put(CommandOption.DUE_DATE, date.toString());
        options.put(CommandOption.STATUS, "PENDING");

        var input = new CommandDTO(CommandAction.ADD, null, options);

        var expected = new Task(
                Optional.empty(),
                input.options().get(CommandOption.CONTENT),
                Optional.of(date),
                TaskState.PENDING,
                List.of());

        when(mockedTaskRepository.save(any(TaskEntity.class))).thenReturn(TaskToJsonTaskEntity.toEntity(expected));

        var result = sut.execute(input);
        assertEquals(expected, result.stream().findFirst().get());

        verify(mockedTaskRepository).save(any(TaskEntity.class));
        verifyNoMoreInteractions(mockedTaskRepository);
    }
}