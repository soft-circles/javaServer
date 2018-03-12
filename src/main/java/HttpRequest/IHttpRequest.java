package HttpRequest;

public interface IHttpRequest {
    String requestLine();
    String method();
    String path();
    String version();
}
