package duke.exception;

public class DukeException extends Exception {
    protected String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
