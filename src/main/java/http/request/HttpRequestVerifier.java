package http.request;

public class HttpRequestVerifier {

    public boolean isPathValid(String path) {
        return path.substring(0, 1).equals("/");
    }
}
