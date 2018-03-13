package HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class HttpRequest implements IHttpRequest {
    private String method, version, path, requestLine;
    private HashMap<String, String> headers, params;

    public HttpRequest(BufferedReader reader) throws IOException {
//        parseRequestLine(reader.readLine());
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
