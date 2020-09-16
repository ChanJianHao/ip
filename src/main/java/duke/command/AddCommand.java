package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage(task.toString());

        int size = tasks.getTotal();
        ui.printMessage("You now have " + size + " tasks in the list");

        storage.updateLocalList(tasks.getList());
    }
}
