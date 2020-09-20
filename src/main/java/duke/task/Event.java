package duke.task;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Event extends Task {
    protected Date eventDatetime;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Event(String description, Date eventDatetime) {
        super(description);
        this.eventDatetime = eventDatetime;
    }

    public Date getDatetime() {
        return eventDatetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateFormat.format(eventDatetime) + ")";
    }

    @Override
    public String toFileString() {
        return "[E]" + super.toFileString() + "(at: " + dateFormat.format(eventDatetime) + ")";
    }
}
