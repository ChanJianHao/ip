package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    public static final String LOCAL_TASK_LIST = "data/tasks.txt";
    public static final String LOCAL_TASK_FOLDER = "data";

    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private TaskList tasks;

    private Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(LOCAL_TASK_LIST, LOCAL_TASK_FOLDER);
        try {
            tasks = new TaskList(storage.readLocalList());
        } catch (IOException e) {
            tasks = new TaskList();
            System.out.println("File IO exception, we had difficulty managing your local task file.");
        }
    }

    private void run() {
        ui.printWelcome();
        ui.checkLocalList(tasks);

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command currentCommand = parser.processCommand(userInput);
                currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (DukeException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
