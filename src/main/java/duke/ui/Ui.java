package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for User Interaction management of the application.
 */
public class Ui {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String CAT_LOGO =
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" + LS
                    + "░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░" + LS
                    + "░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░" + LS
                    + "░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░" + LS
                    + "░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░" + LS
                    + "░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░" + LS
                    + "░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░" + LS
                    + "░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░" + LS
                    + "░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░" + LS
                    + "░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░" + LS
                    + "░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░" + LS
                    + "░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░" + LS
                    + "░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░" + LS
                    + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" + LS;

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
        System.out.println(" Bye. Hope to see you again soon!");
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

    public void addTaskInteraction(TaskList tasks, Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());

        int size = tasks.getTotal();
        System.out.println("You now have " + size + " tasks in the list.");
    }

    public void deleteTaskInteraction(TaskList tasks, Task tempTask) {
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("   " + tempTask);
        System.out.println(" Now you have " + tasks.getTotal() + " tasks in the list.");
    }

    public void doneTaskInteraction(Task tempTask) {
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println("   " + tempTask);
    }

}
