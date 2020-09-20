package duke.storage;

import duke.exception.DukeException;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    private static final String REGEX_DEADLINE_BRACKET_BY = "\\(by:";
    private static final String REGEX_EVENT_BRACKET_AT = "\\(at:";
    public static final String INVALID_SAVED_TASK_DATA = "Invalid saved task data found!";

    private final String localTaskList;
    private final String localTaskFolder;

    public Storage(String localTaskList, String localTaskFolder) {
        this.localTaskList = localTaskList;
        this.localTaskFolder = localTaskFolder;
    }

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

    public String processSplitString(String[] splitInput) {
        String taskDescription;
        if (splitInput.length > 1) {
            taskDescription = splitInput[1];
        } else {
            taskDescription = "";
        }
        return taskDescription;
    }

    public ArrayList<Task> readLocalList() throws IOException, DukeException {
        ArrayList<Task> savedTasks = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get(localTaskFolder));
            Files.createFile(Path.of(localTaskList));
        } catch (FileAlreadyExistsException ignored) {
            // All is good
        }

        File f = new File(localTaskList); // create a File for the given file path

        try {
            Scanner inputFile = new Scanner(f); // create a Scanner using the File as the source
            while (inputFile.hasNext()) {
                String line = inputFile.nextLine();

                char taskType = line.charAt(1);
                char taskStatus = line.charAt(4);
                String taskString = line.substring(7);

                Date taskDate;
                DateFormat taskDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

                if (taskType == 'T') {
                    Task newTask = new Todo(taskString);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else if (taskType == 'E') {
                    String[] eventSplit = taskString.split(REGEX_EVENT_BRACKET_AT, 2);
                    String at = processSplitString(eventSplit);
                    at = at.substring(0, at.length() - 1);

                    taskDate = taskDateFormat.parse(at);

                    Event newTask = new Event(eventSplit[0], taskDate);
                    addExistingTask(savedTasks, newTask, taskStatus);
                } else if (taskType == 'D') {
                    String[] deadlineSplit = taskString.split(REGEX_DEADLINE_BRACKET_BY, 2);
                    String by = processSplitString(deadlineSplit);
                    by = by.substring(0, by.length() - 1);

                    taskDate = taskDateFormat.parse(by);

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

    public void addExistingTask(ArrayList<Task> taskList, Task newTask, char taskStatus) {
        if (taskStatus == 'T') {
            newTask.markAsDone();
        }
        taskList.add(newTask);
    }
}
