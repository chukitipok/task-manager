package infrastructure.delivery;

public class HelperPrinter {
    public static void print() {
        System.out.println("Invalid command, please check help info.");
        System.out.println("Help info:");
        System.out.println("[add, list, update, remove] -c [content] <optional> -d:[yyyy-mm-dd] -s:[todo, pending" +
                "progress, done, cancelled, closed]");
        System.out.println("Examples:");
        System.out.println("task add -c \"hello world\"");
        System.out.println("task list");
        System.out.println("task add -c \"Do project\" -d:2022-01-01");
        System.out.println("task update 1 -c \"hello world\"");
        System.out.println("task update 1 -s:progress");
        System.out.println("task remove 1");
    }
}
