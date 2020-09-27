package duke.task;

/**
 * Abstract class for Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of task.
     *
     * @return Tick symbol if task is done, else Cross symbol.
     */
    public String getStatusIcon() {
        // returns tick or X symbols
        return (isDone ? "✓" : "✗");
    }

    /**
     * Gets status of task whether it is done or not.
     *
     * @return status of task.
     */
    public boolean getStatus() {
        return isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets description of the task.
     *
     * @return returns description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns appropriate format for task to be stored in local .txt file.
     *
     * @return string in local task file format.
     */
    public String toFileString() {
        char doneFlag = 'F';

        if (getStatus()) {
            doneFlag = 'T';
        }

        return "[" + doneFlag + "] " + description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
