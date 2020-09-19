package duke.task;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Deadline extends Task {
    protected Date dueDate;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");;

    public Deadline(String description, Date dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateFormat.format(dueDate) + ")";
    }

    @Override
    public String toFileString() {
        return "[D]" + super.toFileString() + "(by: " + dateFormat.format(dueDate) + ")";
    }
}
