package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    String findString;

    public FindCommand(String findString) {
        this.findString = findString;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task tempTask = tasks.getList().get(i);

            if (tempTask.getDescription().contains(findString)) {
                System.out.println(" " + (i + 1) + "." + tempTask);
            }
        }
    }

}
