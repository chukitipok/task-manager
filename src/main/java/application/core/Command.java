package application.core;

import java.util.Collection;

public interface Command {
    Collection<Option> getOptions();
}
