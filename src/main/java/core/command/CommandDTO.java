package core.command;

import java.util.Map;

public record CommandDTO(CommandAction action, Integer taskId, Map<CommandOption, String> options) {}
