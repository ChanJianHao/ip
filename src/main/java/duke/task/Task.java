package duke.task;

import java.util.Date;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // returns tick or X symbols
        return (isDone ? "✓" : "✗");
    }

    public boolean getStatus() {
        return isDone;
    }

    public abstract Date getDatetime();

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    public String toFileString() {
        char doneFlag = 'F';

        if (getStatus()) {
            doneFlag = 'T';
        }

        return "[" + doneFlag + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
