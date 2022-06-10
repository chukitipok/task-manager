package infrastructure.util;

import core.command.CommandDTO;
import infrastructure.util.InvalidCommandException;

import java.util.Collection;

public interface Parser {

    CommandDTO parse(Collection<String> args) throws InvalidCommandException;
}
