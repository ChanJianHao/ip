package duke.task;

import java.util.Date;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public Date getDatetime() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns appropriate format for task to be stored in local .txt file.
     *
     * @return string in local task file format.
     */
    @Override
    public String toFileString() {
        return "[T]" + super.toFileString();
    }
}
