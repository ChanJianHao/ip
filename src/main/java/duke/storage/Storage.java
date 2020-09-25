package duke.storage;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static duke.parser.Parser.TASK_DATETIME_FORMAT;

/**
 * Handles storage and update for local task list.
 */
public class Storage {
    private static final String REGEX_DEADLINE_BRACKET_BY = "\\(by:";
    private static final String REGEX_EVENT_BRACKET_AT = "\\(at:";
    private static final String INVALID_SAVED_TASK_DATA = "Invalid saved task data found!";

    private static final int TASK_TYPE_INDEX = 1;
    private static final int TASK_STATUS_INDEX = 4;
    private static final int TASK_STRING_INDEX = 7;
    private static final char TODO = 'T';
    private static final char DEADLINE = 'D';
    private static final char EVENT = 'E';

    private final String localTaskList;
    private final String localTaskFolder;

    /**
     * Constructor for local tasks storage.
     *
     * @param localTaskList   Path to tasks storage file.
     * @param localTaskFolder Path to tasks storage folder.
     */
    public Storage(String localTaskList, String localTaskFolder) {
        this.localTaskList = localTaskList;
        this.localTaskFolder = localTaskFolder;
    }

    /**
     * Saves the current TaskList to a save file.
     *
     * @param taskList taskList containing the tasks.
     */
    public void updateLocalList(ArrayList<Task> taskList) {
        try {
            File data = new File(localTaskList);
            FileWriter fw = new FileWriter(data);

            StringBuilder toWrite = new StringBuilder();
            for (Task task : taskList) {
                toWrite.append(task.toFileString()).append(System.lineSeparator());
            }

            fw.write(toWrite.toString());
            fw.close();
        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
    }

    /**
     * Reads the local storage task list.
     *
     * @return taskList containing existing tasks in storage file.
     * @throws IOException   If there are issues with file IO.
     * @throws DukeException If there are issues with existing save data.
     */
    public ArrayList<Task> readLocalList(Parser parser) throws IOException, DukeException {
        ArrayList<Task> savedTasks = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get(localTaskFolder));
            Files.createFile(Path.of(localTaskList));
        } catch (FileAlreadyExistsException ignored) {
            // All is good since file already exists
        }

        // Creates a file for the given file path
        File f = new File(localTaskList);

        try {
            // Creates a Scanner using the File as the source
            Scanner inputFile = new Scanner(f);

            while (inputFile.hasNext()) {
                String line = inputFile.nextLine();

                char taskType = line.charAt(TASK_TYPE_INDEX);
                char taskStatus = line.charAt(TASK_STATUS_INDEX);
                String taskString = line.substring(TASK_STRING_INDEX);

                Date taskDate;

                if (taskType == TODO) {
                    Task newTask = new Todo(taskString);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else if (taskType == EVENT) {
                    String[] eventSplit = taskString.split(REGEX_EVENT_BRACKET_AT, 2);
                    String at = parser.processSplitString(eventSplit);
                    at = at.substring(0, at.length() - 1);

                    taskDate = TASK_DATETIME_FORMAT.parse(at);

                    Event newTask = new Event(eventSplit[0], taskDate);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else if (taskType == DEADLINE) {
                    String[] deadlineSplit = taskString.split(REGEX_DEADLINE_BRACKET_BY, 2);
                    String by = parser.processSplitString(deadlineSplit);
                    by = by.substring(0, by.length() - 1);

                    taskDate = TASK_DATETIME_FORMAT.parse(by);

                    Deadline newTask = new Deadline(deadlineSplit[0], taskDate);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else {
                    throw new DukeException(INVALID_SAVED_TASK_DATA);
                }
            }

        } catch (FileNotFoundException | DukeException | ParseException e) {
            e.printStackTrace();
            throw new DukeException(INVALID_SAVED_TASK_DATA);
        }

        return savedTasks;
    }

    /**
     * Updates task status and add it to taskList.
     *
     * @param taskList   taskList containing tasks.
     * @param newTask    new task to be added.
     * @param taskStatus task status for the new task.
     */
    private void addExistingTask(ArrayList<Task> taskList, Task newTask, char taskStatus) {
        if (taskStatus == 'T') {
            newTask.markAsDone();
        }
        taskList.add(newTask);
    }

}
