package infrastructure.util;

import core.command.CommandAction;
import core.command.CommandDTO;
import core.command.CommandOption;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.*;

public class CommandParser implements Parser {

    @Option(name = "-c", usage = "The description of the task to achieve.")
    private String content;

    @Option(name = "-d", usage = "The deadline to achieve the task.")
    private String dueDate;

    @Option(name = "-s", usage = "The status the task.")
    private String status;

    @Argument(required = true)
    private String action;

    @Argument(index = 1)
    private Integer taskId;

    private final Map<CommandOption, String> options;

    public CommandParser() {
        this.options = new HashMap<>();
    }

    public CommandDTO parse(Collection<String> args) throws InvalidCommandException {
        var arguments = prepareArguments(args);
        var cmdParser = new CmdLineParser(this);

        try {
            cmdParser.parseArgument(arguments);
            var commandAction = CommandAction.of(action).orElseThrow();
            fillOptions();

            return new CommandDTO(commandAction, taskId, options);
        }
        catch (CmdLineException | NoSuchElementException | IllegalArgumentException ignored) {
            throw new InvalidCommandException();
        }
    }

    private void fillOptions() {
        var dueDate = this.dueDate != null ? this.dueDate + "T23:59:59" : null;

        options.put(CommandOption.CONTENT, content);
        options.put(CommandOption.DUE_DATE, dueDate);
        options.put(CommandOption.STATUS, status);
    }

    private Collection<String> prepareArguments(Collection<String> args) {
        var filteredArguments = new ArrayList<String>();

        for (var arg : args) {
            filteredArguments.addAll(List.of(arg.split(":")));
        }

        return filteredArguments;
    }
}
