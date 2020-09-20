package duke.task;

import java.util.Date;

import static duke.parser.Parser.TASK_DATETIME_FORMAT;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected Date dueDate;

    public Deadline(String description, Date dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Gets the datetime of deadline.
     *
     * @return datetime of deadline.
     */
    public Date getDatetime() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + TASK_DATETIME_FORMAT.format(dueDate) + ")";
    }

    @Override
    public String toFileString() {
        return "[D]" + super.toFileString() + "(by: " + TASK_DATETIME_FORMAT.format(dueDate) + ")";
    }
}
