package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleCommand extends Command {
    private final Date checkDate;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
            "dd/MM/yyyy");
    public static final String EXCEPTION_INVALID_DATE = "Did you include a valid date? yyyy/MM/dd";

    public ScheduleCommand(Date checkDate) throws DukeException {
        try {
            this.checkDate = DATE_FORMAT.parse(DATE_FORMAT.format(checkDate));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new DukeException(EXCEPTION_INVALID_DATE);
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(" Your tasks for date " + checkDate + " are:");

        for (int i = 0; i < tasks.getList().size(); i++) {
            Date taskDate;
            Task tempTask = tasks.getList().get(i);

            if (tempTask instanceof Event || tempTask instanceof Deadline) {
                try {
                    taskDate = DATE_FORMAT.parse(DATE_FORMAT.format(tempTask.getDatetime()));
                    if (taskDate.compareTo(checkDate) == 0) {
                        System.out.println(" " + (i + 1) + "." + tempTask);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
