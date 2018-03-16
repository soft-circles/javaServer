package http.status;

public class InvalidStatusCodeException extends Throwable {
    public InvalidStatusCodeException(String message) {
        super(message);
    }
}
