package duke.ui;

import duke.task.TaskList;

import java.io.InputStream;
import java.util.Scanner;

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

    private final Scanner in;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Prints the welcome message which includes logo.
     */
    public void printWelcome() {
        System.out.println(CAT_LOGO);

        showLine();
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Prints the good bye message when bye is received.
     */
    public void printGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void checkLocalList(TaskList tasks) {
        if (tasks.getList().size() > 0) {
            System.out.println(" Successfully loaded " + tasks.getList().size() + " tasks from previous session.");
        } else {
            System.out.println(" Looks like you're new here, starting with a fresh task list!");
        }
        showLine();
    }

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return in.nextLine();
    }
}
