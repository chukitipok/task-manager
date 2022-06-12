package infrastructure.delivery;
import core.command.CommandAction;
import core.task.Task;

public record Delivery(Task task, CommandAction type){
    public void display() {
        String commandType;

        switch (type) {
            case ADD:
                commandType = "add";
                break;

            case LIST:
                commandType = "list";
                break;

            case REMOVE:
                commandType = "remove";
                break;

            case UPDATE:
                commandType = "update";
                break;
            default:
                commandType = "invalid";
                break;
        }

        System.out.println("Task " + commandType + ": " + task.toString());
    }

    public static void displayCommandHelper() {
        System.out.println("Invalid command. Please check help info.");
        System.out.println("Help info:");
        System.out.println("[add, list, update, remove] -c [title] <optional> -d [date: yyyy-mm-dd] -s[todo, pending" +
                "progress, done, cancelled, closed]");
        System.out.println("Examples:");
        System.out.println("task add -c 'hello world'");
        System.out.println("task list");
        System.out.println("task update 1 -c 'hello world'");
        System.out.println("task update 1 -s 'progress'");
        System.out.println("task remove 1");
    }
}
