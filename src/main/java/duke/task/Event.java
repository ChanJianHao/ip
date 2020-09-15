package duke.task;

public class Event extends Task {
    protected String eventDatetime;

    public Event(String description, String eventDatetime) {
        super(description);
        this.eventDatetime = eventDatetime;
    }

    public String getEventDatetime() {
        return eventDatetime;
    }

    public void setEventDatetime(String eventDatetime) {
        this.eventDatetime = eventDatetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + eventDatetime + ")";
    }

    @Override
    public String toFileString() {
        return "[E]" + super.toFileString() + "(at:" + eventDatetime + ")";
    }
}
