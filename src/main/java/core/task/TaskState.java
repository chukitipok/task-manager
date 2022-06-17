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
}