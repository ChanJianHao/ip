package duke.parser;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.Scanner;


public class Parser {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String REGEX_SINGLE_SPACE = " ";
    private static final String REGEX_EVENT_SLASH_AT = "/at";
    private static final String REGEX_DEADLINE_SLASH_BY = "/by";

    public static final String EXCEPTION_INVALID_TASK_NUMBER = "That's an invalid task number!";
    public static final String EXCEPTION_INVALID_COMMAND = "I'm sorry, but I don't know what that means.";
    public static final String EXCEPTION_EMPTY_DATETIME = "Did you forget to include the datetime?";

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Runs main menu for the program. Does a while loop until user inputs bye.
     */
    public void runMainMenu() {
        boolean isProcessingCommand = true;

        Scanner userInput = new Scanner(System.in);

        ui.checkLocalList(tasks);

        while (isProcessingCommand) {
            try {
                isProcessingCommand = processCommand(userInput);
            } catch (DukeException exceptionMessage) {
                exceptionMessage.printStackTrace();
            }
        }
    }

    /**
     * Processes user input from main menu.
     *
     * @param userInput User input string.
     * @return Returns true unless user enters bye, which would terminate main menu.
     */
    private boolean processCommand(Scanner userInput) throws DukeException {
        String input = userInput.nextLine();

        // Split string into 2's using space
        String[] splitInput = input.split(REGEX_SINGLE_SPACE, 2);
        String taskDescription = processSplitString(splitInput);

        // Main menu navigation
        switch (splitInput[0]) {
        case COMMAND_BYE:
            ui.printGoodbye();
            return false;
        case COMMAND_LIST:
            processListCommand();
            break;
        case COMMAND_DONE:
            processDoneCommand(taskDescription);
            break;
        case COMMAND_DELETE:
            processDeleteCommand(taskDescription);
            break;
        case COMMAND_TODO:
            processTodoCommand(taskDescription);
            break;
        case COMMAND_DEADLINE:
            processDeadlineCommand(taskDescription);
            break;
        case COMMAND_EVENT:
            processEventCommand(taskDescription);
            break;
        default:
            System.out.println("Input: " + input);
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
    public String processSplitString(String[] splitInput) {
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
     */
    private void processListCommand() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task tempTask = tasks.getList().get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Adds a todo task.
     *
     * @param taskDescription duke.task.Task description for the newly added task.
     */
    private void processTodoCommand(String taskDescription) throws DukeException {
        checkTaskDescription(taskDescription);
        Todo newTask = new Todo(taskDescription);
        addTask(newTask);
    }

    /**
     * Adds event task.
     *
     * @param taskDescription duke.task.Task description for the newly added task.
     */
    private void processEventCommand(String taskDescription) throws DukeException {
        checkTaskDescription(taskDescription);
        String[] eventSplit = taskDescription.split(REGEX_EVENT_SLASH_AT, 2);
        String at = processSplitString(eventSplit);
        checkTaskDatetime(at);

        Event newTask = new Event(eventSplit[0], at);
        addTask(newTask);
    }

    /**
     * Adds deadline task.
     *
     * @param taskDescription duke.task.Task description for the newly added task.
     */
    private void processDeadlineCommand(String taskDescription) throws DukeException {
        checkTaskDescription(taskDescription);
        String[] deadlineSplit = taskDescription.split(REGEX_DEADLINE_SLASH_BY, 2);
        String by = processSplitString(deadlineSplit);
        checkTaskDatetime(by);

        Deadline newTask = new Deadline(deadlineSplit[0], by);
        addTask(newTask);
    }

    /**
     * Checks task description validity.
     *
     * @param taskDescription duke.task.Task description.
     * @throws DukeException duke.exception.DukeException when the string taskDescription is empty.
     */
    private void checkTaskDescription(String taskDescription) throws DukeException {
        if (taskDescription.isEmpty()) {
            throw new DukeException(EXCEPTION_INVALID_COMMAND);
        }
    }

    /**
     * Checks task description validity.
     *
     * @param dateTime duke.task.Task datetime.
     * @throws DukeException duke.exception.DukeException when the string datetime is empty.
     */
    private void checkTaskDatetime(String dateTime) throws DukeException {
        if (dateTime.isEmpty()) {
            throw new DukeException(EXCEPTION_EMPTY_DATETIME);
        }
    }

    /**
     * Executes adding task to provided task list and prints success message.
     *
     * @param newTask New task to be added.
     */
    private void addTask(Task newTask) {
        tasks.addTask(newTask);
        storage.updateLocalList(tasks.getList());

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.getTotal() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE + System.lineSeparator());
    }


    /**
     * Checks if user input task number is an existing task.
     *
     * @param taskDescription duke.task.Task index to be marked as done.
     * @return True if task number is valid.
     * @throws DukeException Throws exception if invalid task number.
     */
    private boolean checkValidTaskNumber(String taskDescription) throws DukeException {
        // Checks if invalid done number is provided
        if (taskDescription.equals("")) {
            throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
        } else if (Integer.parseInt(taskDescription) > tasks.getTotal()
                || Integer.parseInt(taskDescription) <= 0) {
            throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
        }

        return true;
    }

    /**
     * Marks an existing task as done.
     *
     * @param taskDescription duke.task.Task index to be marked as done.
     */
    private void processDoneCommand(String taskDescription) throws DukeException {
        if (checkValidTaskNumber(taskDescription)) {
            Task tempTask = tasks.getList().get(Integer.parseInt(taskDescription) - 1);

            tempTask.markAsDone();

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Nice! I've marked this task as done: ");
            System.out.println("   " + tempTask);
            System.out.println(HORIZONTAL_LINE + System.lineSeparator());
            storage.updateLocalList(tasks.getList());
        }
    }

    /**
     * Delete specified task
     */
    private void processDeleteCommand(String taskDescription) throws DukeException {
        if (checkValidTaskNumber(taskDescription)) {
            Task tempTask = tasks.getList().get(Integer.parseInt(taskDescription) - 1);
            int taskNumber = Integer.parseInt(taskDescription) - 1;
            int remainingTasks = tasks.getTotal() - 1;

            System.out.println(HORIZONTAL_LINE);
            System.out.println(" Noted. I've removed this task: ");
            System.out.println("   " + tempTask);
            System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE + System.lineSeparator());

            tasks.removeTask(taskNumber);
            storage.updateLocalList(tasks.getList());
        }
    }
}
