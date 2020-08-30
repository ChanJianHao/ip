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


    public static void main(String[] args) {
        printWelcome();
        runMainMenu();
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
        String taskDescription;
        String input = userInput.nextLine();

        String[] splitInput = input.split(" ", 2);

        if (splitInput.length > 1) {
            taskDescription = splitInput[1];
        } else {
            taskDescription = "";
        }

        // Main menu navigation
        if (splitInput[0].equals("bye")) {
            printGoodbye();
            return false;
        } else if (splitInput[0].equals("list")) {
            for (int i = 0; i < taskList.size(); i++) {
                Task tempTask = taskList.get(i);
                System.out.println(" " + (i + 1) + "." + tempTask);
            }
        } else if (splitInput[0].equals("done")) {
            if (taskDescription == "") {
                System.out.println("That's an invalid task number! *meoww*");
            } else if (Integer.parseInt(taskDescription) > Task.getGetTotalTask()
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
        } else if (splitInput[0].equals("todo")) {
            Todo newTask = new Todo(taskDescription);
            taskList.add(newTask);

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + newTask);
            System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
        } else if (splitInput[0].equals("deadline")) {
            String[] deadlineSplit = taskDescription.split("/by", 2);

            String by;

            if (deadlineSplit.length > 1) {
                by = deadlineSplit[1];
            } else {
                by = "";
            }

            Deadline newTask = new Deadline(deadlineSplit[0], by);
            taskList.add(newTask);

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + newTask);
            System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
        } else if (splitInput[0].equals("event")) {
            String[] eventSplit = taskDescription.split("/at", 2);
            String at;

            if (eventSplit.length > 1) {
                at = eventSplit[1];
            } else {
                at = "";
            }

            Event newTask = new Event(eventSplit[0], at);
            taskList.add(newTask);

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + newTask);
            System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println("That's an invalid task input! *angry meoww noises*");
        }

        return true;
    }

    private static void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printWelcome() {
        System.out.println(CAT_LOGO);

        // Greet
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
