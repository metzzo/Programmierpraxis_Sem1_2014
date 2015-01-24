package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.helper.Strings;

/**
 * Created by Robert on 08.01.2015.
 */
public class OperationException extends Exception {
    public OperationException(final String msg) {
        super(msg);
    }

    public static class InvalidOperationException extends OperationException {
        public InvalidOperationException() {
            super(Strings.INVALID_OPERATION);
        }
    }
    public static class InvalidCommandException extends OperationException {
        public InvalidCommandException() {
            super(Strings.INVALID_COMMAND);
        }
    }
    public static class InvalidInputException extends OperationException {
        public InvalidInputException() {
            super(Strings.INVALID_INPUT);
        }
    }
}
