package http.status;

public class HttpStatus {

    private HttpStatus() {}

    public static String message(String code) throws InvalidStatusCodeException {
        String message = StatusMessages.STATUSES.get(Integer.parseInt(code)).toString();
        if (message == null) {
           throw new InvalidStatusCodeException("Status code does not exist");
       } else {
           return message;
       }
    }

    public static boolean isErrorStatus(int code) {
        return code >= 400 && code < 600;
    }

    public static boolean isInfoStatus(int code) {
        return code >= 100 && code < 200;
    }

    public static boolean isRedirect(int code) {
        return code >= 300 && code < 400;
    }

    public static boolean isServerError(int code) {
        return code >= 500 && code < 600;
    }

    public static boolean isSuccess(int code) {
        return code >= 200 && code < 300;
    }
}
