package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTotal() == 0) {
            ui.printMessage("You have no tasks to do, how great!");
        } else {
            for (int i = 0; i < tasks.getList().size(); i++) {
                Task tempTask = tasks.getList().get(i);
                System.out.println(" " + (i + 1) + "." + tempTask);
            }
        }
    }
}
