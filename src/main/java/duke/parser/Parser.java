package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";

    private static final String REGEX_SINGLE_SPACE = " ";
    private static final String REGEX_EVENT_SLASH_AT = "/at";
    private static final String REGEX_DEADLINE_SLASH_BY = "/by";

    public static final String EXCEPTION_INVALID_TASK_NUMBER = "That's an invalid task number!";
    public static final String EXCEPTION_INVALID_COMMAND = "I'm sorry, but I don't know what that means.";
    public static final String EXCEPTION_EMPTY_DATETIME = "Did you forget to include the datetime?";


    /**
     * Processes user input from main menu.
     *
     * @return Returns true unless user enters bye, which would terminate main menu.
     */
    public Command processCommand(String input) throws DukeException {
        // Split string into 2's using space
        String[] splitInput = input.split(REGEX_SINGLE_SPACE, 2);
        String taskDescription = processSplitString(splitInput);

        // Main menu navigation
        switch (splitInput[0]) {
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_FIND:
            return new FindCommand(taskDescription);
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_DONE:
            return new DoneCommand(taskDescription);
        case COMMAND_DELETE:
            return new DeleteCommand(taskDescription);
        case COMMAND_TODO:
            checkTaskDescription(taskDescription);
            return new AddCommand(new Todo(taskDescription));
        case COMMAND_DEADLINE:
            checkTaskDescription(taskDescription);
            String[] deadlineSplit = taskDescription.split(REGEX_DEADLINE_SLASH_BY, 2);
            String by = processSplitString(deadlineSplit);
            checkTaskDatetime(by);

            return new AddCommand(new Deadline(deadlineSplit[0], by));
        case COMMAND_EVENT:
            checkTaskDescription(taskDescription);
            String[] eventSplit = taskDescription.split(REGEX_EVENT_SLASH_AT, 2);
            String at = processSplitString(eventSplit);
            checkTaskDatetime(at);

            return new AddCommand(new Event(eventSplit[0], at));
        default:
            throw new DukeException(EXCEPTION_INVALID_COMMAND);
        }
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
}
