package duke.task;

public class Task {
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

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
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
