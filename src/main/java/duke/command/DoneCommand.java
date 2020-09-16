package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.parser.Parser.EXCEPTION_INVALID_TASK_NUMBER;

public class DoneCommand extends Command {

    private final int taskNumber;

    public DoneCommand(String data) throws DukeException {
        data = data.trim();
        String numberPattern = "^[0-9]+$";

        if (!data.matches(numberPattern)) {
            throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
        } else {
            try {
                this.taskNumber = Integer.parseInt(data);
            } catch (NumberFormatException exceptionMessage) {
                throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
            }
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > tasks.getTotal() || taskNumber <= 0) {
            throw new DukeException(EXCEPTION_INVALID_TASK_NUMBER);
        } else {
            Task tempTask = tasks.getList().get(taskNumber - 1);
            tempTask.markAsDone();

            System.out.println(" Nice! I've marked this task as done: ");
            System.out.println("   " + tempTask);

            storage.updateLocalList(tasks.getList());
        }
    }
}
