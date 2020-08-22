import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static final String NEKOBOT_LOGO =
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
        // Print logo
        System.out.println(NEKOBOT_LOGO);

        // DUKE Greet
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String input = userInput.nextLine();

            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);

                return;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    Task tempTask = taskList.get(i);
                    System.out.println(" " + (i + 1) + ". " + tempTask.getStatusIconAndDesc());
                }
            } else if (input.startsWith("done ")) {
                int doneTaskNumber = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task tempTask = taskList.get(doneTaskNumber - 1);

                tempTask.markAsDone();

                System.out.println(HORIZONTAL_LINE);
                System.out.println(" Nice! I've marked this task as done: ");
                System.out.println("   " + tempTask.getStatusIconAndDesc());
                System.out.println(HORIZONTAL_LINE);
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);

                System.out.println(HORIZONTAL_LINE);
                System.out.println(" added: " + input);
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
