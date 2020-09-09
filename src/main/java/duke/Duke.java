package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE =
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
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String REGEX_SINGLE_SPACE = " ";
    private static final String REGEX_EVENT_SLASH_AT = "/at";
    private static final String REGEX_EVENT_BRACKET_AT = "\\(at:";
    private static final String REGEX_DEADLINE_SLASH_BY = "/by";
    private static final String REGEX_DEADLINE_BRACKET_BY = "\\(by:";

    public static final String EXCEPTION_INVALID_TASK_NUMBER = "That's an invalid task number!";
    public static final String EXCEPTION_INVALID_COMMAND = "I'm sorry, but I don't know what that means.";
    public static final String EXCEPTION_EMPTY_DESCRIPTION = "The description of a task cannot be empty.";
    public static final String EXCEPTION_EMPTY_DATETIME = "Did you forget to include the datetime?";
    public static final String LOCAL_TASK_LIST = "data/tasks.txt";

    public static void main(String[] args) {
        printWelcome();
        runMainMenu();
    }

    /**
     * Prints the welcome message which includes logo.
     */
    private static void printWelcome() {
        System.out.println(CAT_LOGO);

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Runs main menu for the program. Does a while loop until user inputs bye.
     */
    private static void runMainMenu() {
        boolean isProcessingCommand = true;
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner userInput = new Scanner(System.in);

        taskList = readLocalList();

        while (isProcessingCommand) {
            try {
                isProcessingCommand = processCommand(taskList, userInput);
            } catch (DukeException exceptionMessage) {
                System.out.println(exceptionMessage);
            }

        }
    }


    /**
     * Processes user input from main menu.
     *
     * @param taskList  ArrayList of all the tasks.
     * @param userInput User input string.
     * @return Returns true unless user enters bye, which would terminate main menu.
     */
    private static boolean processCommand(ArrayList<Task> taskList, Scanner userInput) throws DukeException {
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
            throw new DukeException(EXCEPTION_INVALID_COMMAND);
        }
        return true;
    }

    /**
     * Checks split string length to handle cases when task description is empty.
     *
     * @param splitInput Array containing split string.
     * @return Returns task description from the split string.
     */
    private static String processSplitString(String[] splitInput) {
        String taskDescription;
        if (splitInput.length > 1) {
            taskDescription = splitInput[1];
        } else {
            taskDescription = "";
        }
        return taskDescription;
    }

    /**
     * Lists all tasks.
     *
     * @param taskList ArrayList containing tasks.
     */
    private static void processListCommand(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    /**
     * Adds todo task.
     *
     * @param taskList        ArrayList containing tasks.
     * @param taskDescription duke.task.Task description for the newly added task.
     */
    private static void processTodoCommand(ArrayList<Task> taskList, String taskDescription) throws DukeException {
        checkTaskDescription(taskDescription);
        Todo newTask = new Todo(taskDescription);
        addTask(taskList, newTask);
    }

    /**
     * Adds event task.
     *
     * @param taskList        ArrayList containing tasks.
     * @param taskDescription duke.task.Task description for the newly added task.
     */
    private static void processEventCommand(ArrayList<Task> taskList, String taskDescription) throws DukeException {
        checkTaskDescription(taskDescription);
        String[] eventSplit = taskDescription.split(REGEX_EVENT_SLASH_AT, 2);
        String at = processSplitString(eventSplit);
        checkTaskDatetime(at);

        Event newTask = new Event(eventSplit[0], at);
        addTask(taskList, newTask);
    }

    /**
     * Adds deadline task.
     *
     * @param taskList        ArrayList containing tasks.
     * @param taskDescription duke.task.Task description for the newly added task.
     */
    private static void processDeadlineCommand(ArrayList<Task> taskList, String taskDescription) throws DukeException {
        checkTaskDescription(taskDescription);
        String[] deadlineSplit = taskDescription.split(REGEX_DEADLINE_SLASH_BY, 2);
        String by = processSplitString(deadlineSplit);
        checkTaskDatetime(by);

        Deadline newTask = new Deadline(deadlineSplit[0], by);
        addTask(taskList, newTask);
    }

    /**
     * Checks task description validity.
     *
     * @param taskDescription duke.task.Task description.
     * @throws DukeException duke.exception.DukeException when the string taskDescription is empty.
     */
    private static void checkTaskDescription(String taskDescription) throws DukeException {
        if (taskDescription.isEmpty()) {
            throw new DukeException(EXCEPTION_EMPTY_DESCRIPTION);
        }
    }

    /**
     * Checks task description validity.
     *
     * @param dateTime duke.task.Task datetime.
     * @throws DukeException duke.exception.DukeException when the string datetime is empty.
     */
    private static void checkTaskDatetime(String dateTime) throws DukeException {
        if (dateTime.isEmpty()) {
            throw new DukeException(EXCEPTION_EMPTY_DATETIME);
        }
    }

    /**
     * Executes adding task to provided task list and prints success message.
     *
     * @param taskList ArrayList containing tasks.
     * @param newTask  New task to be added.
     */
    private static void addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);
        updateLocalList(taskList);

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void updateLocalList(ArrayList<Task> taskList) {
        try {
            File data = new File("data/tasks.txt");
            FileWriter fw = new FileWriter(data);

            StringBuilder toWrite = new StringBuilder("");
            for (Task task : taskList) {
                toWrite.append(task.toString() + " \n");
            }

            fw.write(toWrite.toString());
            fw.close();
        } catch (IOException ioExp) {
            System.err.println(ioExp);
        }
    }

    public static ArrayList<Task> readLocalList() {
        ArrayList<Task> savedTasks = new ArrayList<>();

        File f = new File(LOCAL_TASK_LIST); // create a File for the given file path

        try {
            Scanner inputFile = new Scanner(f); // create a Scanner using the File as the source
            while (inputFile.hasNext()) {
                String line = inputFile.nextLine();

                char taskType = line.charAt(1);
                char taskStatus = line.charAt(4);
                String taskString = line.substring(6);

                if (taskType == 'T') {
                    Task newTask = new Todo(taskString);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else if (taskType == 'E') {
                    String[] eventSplit = taskString.split(REGEX_EVENT_BRACKET_AT, 2);
                    String at = processSplitString(eventSplit);
                    at = at.substring(0, at.length() - 2);
                    checkTaskDatetime(at);

                    Event newTask = new Event(eventSplit[0], at);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else if (taskType == 'D') {
                    String[] deadlineSplit = taskString.split(REGEX_DEADLINE_BRACKET_BY, 2);
                    String by = processSplitString(deadlineSplit);
                    by = by.substring(0, by.length() - 2);
                    checkTaskDatetime(by);

                    Deadline newTask = new Deadline(deadlineSplit[0], by);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else {
                    throw new DukeException("Invalid saved task data found!");
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }

        return savedTasks;
    }

    private static void addExistingTask(ArrayList<Task> taskList, Task newTask, char taskStatus) {
        if (taskStatus == 'T') {
            newTask.markAsDone();
        }
        taskList.add(newTask);
    }

    /**
     * Marks an existing task as done.
     *
     * @param taskList        ArrayList containing tasks.
     * @param taskDescription duke.task.Task index to be marked as done.
     */
    private static void processDoneCommand(ArrayList<Task> taskList, String taskDescription) throws DukeException {
        // Checks if invalid done number is provided
        if (taskDescription.equals("")) {
            throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
        } else if (Integer.parseInt(taskDescription) > taskList.size()
                || Integer.parseInt(taskDescription) <= 0) {
            throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
        } else {
            Task tempTask = taskList.get(Integer.parseInt(taskDescription) - 1);

            tempTask.markAsDone();

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Nice! I've marked this task as done: ");
            System.out.println("   " + tempTask);
            System.out.println(HORIZONTAL_LINE);
        }
    }

    /**
     * Prints the good bye message when bye is received.
     */
    private static void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
