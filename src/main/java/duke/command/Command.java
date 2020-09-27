package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class for commands.
 */
public abstract class Command {
    boolean isExit = false;

    /**
     * Tracks if command is due for exit.
     *
     * @return boolean indicating to exit program or not.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Abstraction of execute method for execution of commands.
     *
     * @param tasks   TaskList storing the tasks.
     * @param ui      User interaction management.
     * @param storage Local storage of tasks.
     * @throws DukeException If command parameters or execution fail.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
