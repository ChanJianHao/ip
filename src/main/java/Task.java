public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIconAndDesc() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
