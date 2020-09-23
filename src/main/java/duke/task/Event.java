package duke.task;

import java.util.Date;

import static duke.parser.Parser.TASK_DATETIME_FORMAT;

/**
 * Represents a Event task.
 */
public class Event extends Task {
    protected Date eventDatetime;

    public Event(String description, Date eventDatetime) {
        super(description);
        this.eventDatetime = eventDatetime;
    }

    /**
     * Gets the datetime of deadline.
     *
     * @return datetime of deadline.
     */
    public Date getDatetime() {
        return eventDatetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + TASK_DATETIME_FORMAT.format(eventDatetime) + ")";
    }

    /**
     * Returns appropriate format for task to be stored in local .txt file.
     *
     * @return string in local task file format.
     */
    @Override
    public String toFileString() {
        return "[E]" + super.toFileString() + "(at: " + TASK_DATETIME_FORMAT.format(eventDatetime) + ")";
    }
}
