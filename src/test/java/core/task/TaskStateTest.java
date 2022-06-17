package core.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static core.task.TaskState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskStateTest {

    @ParameterizedTest
    @MethodSource(value = "provideExpectedValueDependingOnTaskState")
    public void should_test(String expected, TaskState source) {
        assertEquals(expected, source.value());
    }

    private static Stream<Arguments> provideExpectedValueDependingOnTaskState() {
        return Stream.of(
                Arguments.of("todo", TODO),
                Arguments.of("pending", PENDING),
                Arguments.of("progress", PROGRESS),
                Arguments.of("done", DONE),
                Arguments.of("cancelled", CANCELLED),
                Arguments.of("closed", CLOSED)
        );
    }
}