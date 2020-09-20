package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.Date;

import static duke.parser.Parser.EXCEPTION_INVALID_DATETIME;
import static duke.parser.Parser.TASK_DATE_FORMAT;

/**
 * Searches and returns tasks for the day.
 */
public class ScheduleCommand extends Command {
    private final Date checkDate;

    /**
     * Constructor that validates date input.
     *
     * @param checkDate Date to search for.
     * @throws DukeException If input is not valid date format.
     */
    public ScheduleCommand(Date checkDate) throws DukeException {
        try {
            this.checkDate = TASK_DATE_FORMAT.parse(TASK_DATE_FORMAT.format(checkDate));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new DukeException(EXCEPTION_INVALID_DATETIME);
        }
    }

    /**
     * Prints all the task on the specified date.
     *
     * @param tasks   TaskList storing the tasks.
     * @param ui      User interaction management.
     * @param storage Local storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(" Your tasks for date " + checkDate + " are:");

        for (int i = 0; i < tasks.getList().size(); i++) {
            Date taskDate;
            Task tempTask = tasks.getList().get(i);

            if (tempTask instanceof Event || tempTask instanceof Deadline) {
                try {
                    taskDate = TASK_DATE_FORMAT.parse(TASK_DATE_FORMAT.format(tempTask.getDatetime()));
                    if (taskDate.compareTo(checkDate) == 0) {
                        ui.printMessage(" " + (i + 1) + "." + tempTask);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
