package duke.exception;

/**
 * DukeException class for handling tasks-related exceptions.
 */
public class DukeException extends Exception {
    protected String message;

    /**
     * Constructor for exception message.
     *
     * @param message exception message.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns exception message.
     *
     * @return exception message.
     */
    public String toString() {
        return this.message;
    }
}
