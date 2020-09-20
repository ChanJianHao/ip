package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to TaskList.
 */
public class AddCommand extends Command {
    private final Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Executes the command by adding task to TaskList and saving the updates to the save file.
     *
     * @param tasks TaskList storing the tasks.
     * @param ui User interaction management.
     * @param storage Local storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        storage.updateLocalList(tasks.getList());
        ui.addTaskInteraction(tasks, newTask);
    }

}
