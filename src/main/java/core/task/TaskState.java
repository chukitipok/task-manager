package core.task;

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
}