package core.task;

import core.command.CommandAction;

import java.util.Optional;

public enum TaskState {
    TODO("todo"),
    PENDING("pending"),
    PROGRESS("progress"),
    DONE("done"),
    CANCELLED("cancelled"),
    CLOSED("closed");

    private final String value;

    TaskState(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public int toValue() {
        return ordinal();
    }

    public static Optional<TaskState> of(String value) {
        for (var state : TaskState.values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return Optional.of(state);
            }
        }

        return Optional.empty();
    }
}