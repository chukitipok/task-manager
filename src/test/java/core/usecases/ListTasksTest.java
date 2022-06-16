package core.usecases;

import core.ports.TaskReader;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ListTasksTest {

    @Mock
    private TaskReader reader;

    @InjectMocks
    private ListTasks sut;

    @Test
    public void should_return_empty_list() {
        // todo
    }

    @Test
    public void should_return_non_empty_list() {
        // todo
    }

}