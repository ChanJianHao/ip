package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }
}
