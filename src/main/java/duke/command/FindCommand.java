package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds tasks by their name using keyword search.
 */
public class FindCommand extends Command {
    String findString;

    /**
     * Constructor sets the keyword for search.
     *
     * @param findString search keyword.
     */
    public FindCommand(String findString) {
        this.findString = findString;
    }

    /**
     * Finds and prints tasks which matches keyword.
     *
     * @param tasks   TaskList storing the tasks.
     * @param ui      User interaction management.
     * @param storage Local storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(" The following are results for keyword: " + findString);
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task tempTask = tasks.getList().get(i);

            if (tempTask.getDescription().contains(findString)) {
                ui.printMessage(" " + (i + 1) + "." + tempTask);
            }
        }
    }

}
