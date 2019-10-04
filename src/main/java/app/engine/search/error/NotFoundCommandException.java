package app.engine.search.error;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

public class NotFoundCommandException {
    private final static String SAMPLE_EXCEPTION = "Please Enter the true Command: ";


    public NotFoundCommandException() {
        System.err.println(SAMPLE_EXCEPTION);
    }

    public NotFoundCommandException(String message) {
        System.err.println(SAMPLE_EXCEPTION + message);
    }

    public NotFoundCommandException(String message, Throwable cause) {
        System.err.println(SAMPLE_EXCEPTION + "[" + message + "]" + " {" + cause + "}");
    }
}
