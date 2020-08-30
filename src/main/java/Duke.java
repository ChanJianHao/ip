import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private static final String CAT_LOGO =
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
                    + "░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░\n"
                    + "░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░\n"
                    + "░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░\n"
                    + "░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░\n"
                    + "░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░\n"
                    + "░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░\n"
                    + "░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░\n"
                    + "░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░\n"
                    + "░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░\n"
                    + "░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░\n"
                    + "░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░\n"
                    + "░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░\n"
                    + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String REGEX_SINGLE_SPACE = " ";
    private static final String REGEX_EVENT_SLASH_AT = "/at";
    private static final String REGEX_DEADLINE_SLASH_BY = "/by";

    public static void main(String[] args) {
        printWelcome();
        runMainMenu();
    }

    private static void printWelcome() {
        System.out.println(CAT_LOGO);

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void runMainMenu() {
        boolean isProcessingCommand = true;
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        while (isProcessingCommand) {
            isProcessingCommand = processCommand(taskList, userInput);
        }
    }

    private static boolean processCommand(ArrayList<Task> taskList, Scanner userInput) {
        String input = userInput.nextLine();

        // Split string into 2's using space
        String[] splitInput = input.split(REGEX_SINGLE_SPACE, 2);
        String taskDescription = processSplitString(splitInput);

        // Main menu navigation
        switch (splitInput[0]) {
        case COMMAND_BYE:
            printGoodbye();
            return false;
        case COMMAND_LIST:
            processListCommand(taskList);
            break;
        case COMMAND_DONE:
            processDoneCommand(taskList, taskDescription);
            break;
        case COMMAND_TODO:
            processTodoCommand(taskList, taskDescription);
            break;
        case COMMAND_DEADLINE:
            processDeadlineCommand(taskList, taskDescription);
            break;
        case COMMAND_EVENT:
            processEventCommand(taskList, taskDescription);
            break;
        default:
            System.out.println("That's an invalid task input! *angry meoww noises*");
            break;
        }
        return true;
    }

    private static String processSplitString(String[] splitInput) {
        String taskDescription;
        if (splitInput.length > 1) {
            taskDescription = splitInput[1];
        } else {
            taskDescription = "";
        }
        return taskDescription;
    }

    private static void processListCommand(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    private static void processTodoCommand(ArrayList<Task> taskList, String taskDescription) {
        Todo newTask = new Todo(taskDescription);
        addTask(taskList, newTask);
    }

    private static void processEventCommand(ArrayList<Task> taskList, String taskDescription) {
        String[] eventSplit = taskDescription.split(REGEX_EVENT_SLASH_AT, 2);
        String at = processSplitString(eventSplit);

        Event newTask = new Event(eventSplit[0], at);
        addTask(taskList, newTask);
    }

    private static void processDeadlineCommand(ArrayList<Task> taskList, String taskDescription) {
        String[] deadlineSplit = taskDescription.split(REGEX_DEADLINE_SLASH_BY, 2);
        String by = processSplitString(deadlineSplit);

        Deadline newTask = new Deadline(deadlineSplit[0], by);
        addTask(taskList, newTask);
    }

    private static void addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void processDoneCommand(ArrayList<Task> taskList, String taskDescription) {
        // Checks if invalid done number is provided
        if (taskDescription.equals("")) {
            System.out.println("That's an invalid task number! *meoww*");
        } else if (Integer.parseInt(taskDescription) > taskList.size()
                || Integer.parseInt(taskDescription) <= 0) {
            System.out.println("That's an invalid task number! *meoww*");
        } else {
            Task tempTask = taskList.get(Integer.parseInt(taskDescription) - 1);

            tempTask.markAsDone();

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Nice! I've marked this task as done: ");
            System.out.println("   " + tempTask);
            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
