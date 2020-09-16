package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
