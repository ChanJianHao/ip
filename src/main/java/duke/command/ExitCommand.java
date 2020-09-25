package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Commences exit of Neko-bot.
 */
public class ExitCommand extends Command {

    /**
     * Constructor sets the boolean to indicate exit of program.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Prints goodbye message to user.
     *
     * @param tasks   TaskList storing the tasks.
     * @param ui      User interaction management.
     * @param storage Local storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }
}
