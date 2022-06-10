package core.task;

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

    public static Optional<TaskState> of(String value) {
        for (var action : TaskState.values()) {
            if (action.value.equalsIgnoreCase(value)) {
                return Optional.of(action);
            }
        }

        return Optional.empty();
    }
}