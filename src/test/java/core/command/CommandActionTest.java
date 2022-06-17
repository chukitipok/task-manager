package core.command;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandActionTest {

    @ParameterizedTest
    @ValueSource(strings = { "ad", "lust", "removed" })
    public void should_return_not_any_command_action(String testValue) {
        assertEquals(Optional.empty(), CommandAction.of(testValue));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "add", "update", "list", "remove",
            "Add", "updAte", "lisT", "REMOVE"
    })
    public void should_return_a_valid_command_action(String testValue) {
        var expected = testValue.toLowerCase();
        var result = CommandAction.of(testValue).orElseThrow();

        assertEquals(expected, result.value());
    }
}