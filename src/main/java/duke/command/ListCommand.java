package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists all tasks from task list.
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks from task list.
     *
     * @param tasks   TaskList storing the tasks.
     * @param ui      User interaction management.
     * @param storage Local storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTotal() == 0) {
            ui.printMessage("You have no tasks to do, how great!");
        } else {
            for (int i = 0; i < tasks.getList().size(); i++) {
                Task tempTask = tasks.getList().get(i);
                ui.printMessage(" " + (i + 1) + "." + tempTask);
            }
        }
    }

}
