package HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class HttpRequest implements IHttpRequest {
    private String method, version, path, requestLine;
    private HashMap<String, String> headers, params;

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader.readLine());
    }

    private void parseRequestLine(String requestLine) {
        this.requestLine = requestLine;
        this.method = requestLine.split(" ")[0];
        String pathAndParameters[] = requestLine.split(" ")[1].split("\\?", 2);
        this.path = pathAndParameters[0];
        this.version = requestLine.split(" ")[2];
    }

    @Override
    public String requestLine() {
        return requestLine;
    }

    @Override
    public String method() {
        return method;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public String version() {
        return version;
    }
}
