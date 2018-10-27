package pages.exception;

/**
 * Created by Ramich on 26.10.2018.
 */
public class AutotestError extends Exception {

    public AutotestError(String message) {
        super(message);
    }

    public AutotestError(String message, Throwable cause) {
        super(message, cause);
    }
}
