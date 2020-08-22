public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTask++;
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

    public static int getGetTotalTask() {
        return totalTask;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
