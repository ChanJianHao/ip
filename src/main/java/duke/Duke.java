package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    public static final String LOCAL_TASK_LIST = "data/tasks.txt";
    public static final String LOCAL_TASK_FOLDER = "data";

    private final Parser parser;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        Storage storage = new Storage(LOCAL_TASK_LIST, LOCAL_TASK_FOLDER);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.readLocalList());
        } catch (IOException e) {
            tasks = new TaskList();
            System.out.println("File IO exception, we had difficulty managing your local task file.");
        }
        parser = new Parser(tasks, ui, storage);
    }

    public void run() {
        ui.printWelcome();
        parser.runMainMenu();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
