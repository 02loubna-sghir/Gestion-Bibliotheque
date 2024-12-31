package exceptions;

public class LivreExisteException extends Exception {

    // Default constructor
    public LivreExisteException() {
        super();
    }

    // Constructor that accepts a custom message
    public LivreExisteException(String message) {
        super(message);
    }

    // Constructor that accepts a custom message and a cause
    public LivreExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public LivreExisteException(Throwable cause) {
        super(cause);
    }
}
