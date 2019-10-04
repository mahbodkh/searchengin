package app.engine.search.error;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

public class InputException extends RuntimeException {
    private final static String SAMPLE_EXCEPTION = "The input value exception. ";

    public InputException() {
        System.err.println(SAMPLE_EXCEPTION);
    }

    public InputException(String message) {
        System.err.println(SAMPLE_EXCEPTION + message);

    }

    public InputException(String message, Throwable cause) {
        System.err.println(SAMPLE_EXCEPTION + "[" + message + "]" + " {" + cause + "}");
    }
}
