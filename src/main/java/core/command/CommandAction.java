package core.command;

import java.util.Optional;

public enum CommandAction {
    ADD("add"),
    LIST("list"),
    REMOVE("remove"),
    UPDATE("update");

    private final String value;

    CommandAction(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<CommandAction> of(String value) {
        for (var action : CommandAction.values()) {
            if (action.value.equalsIgnoreCase(value)) {
                return Optional.of(action);
            }
        }

        return Optional.empty();
    }
}
