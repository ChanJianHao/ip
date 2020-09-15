package duke.ui;

import duke.task.TaskList;

public class Ui {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private static final String CAT_LOGO =
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" + System.lineSeparator()
                    + "░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░" + System.lineSeparator()
                    + "░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░" + System.lineSeparator()
                    + "░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░" + System.lineSeparator()
                    + "░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░" + System.lineSeparator()
                    + "░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░" + System.lineSeparator()
                    + "░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░" + System.lineSeparator()
                    + "░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░" + System.lineSeparator()
                    + "░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░" + System.lineSeparator()
                    + "░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░" + System.lineSeparator()
                    + "░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░" + System.lineSeparator()
                    + "░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░" + System.lineSeparator()
                    + "░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░" + System.lineSeparator()
                    + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" + System.lineSeparator();


    /**
     * Prints the welcome message which includes logo.
     */
    public void printWelcome() {
        System.out.println(CAT_LOGO);

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the good bye message when bye is received.
     */
    public void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void checkLocalList(TaskList tasks) {
        if (tasks.getList().size() > 0) {
            System.out.println("Successfully loaded " + tasks.getList().size() + " tasks from previous session.");
        } else {
            System.out.println("Looks like you're new here, starting with a fresh task list!");
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
