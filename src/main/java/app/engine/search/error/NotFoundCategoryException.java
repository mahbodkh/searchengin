package app.engine.search.error;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class NotFoundCategoryException extends RuntimeException {

    private final static String SAMPLE_EXCEPTION = "No category given to index. ";


    public NotFoundCategoryException() {
        System.err.println(SAMPLE_EXCEPTION);
    }

    public NotFoundCategoryException(String message) {
        System.err.println(SAMPLE_EXCEPTION + message);
    }

    public NotFoundCategoryException(String message, Throwable cause) {
        System.err.println(SAMPLE_EXCEPTION + "[" + message + "]" + " {" + cause + "}");
    }

}
