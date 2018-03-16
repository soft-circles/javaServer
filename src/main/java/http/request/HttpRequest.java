package http.request;

import java.util.HashMap;

public class HttpRequest implements IHttpRequest {
    private String method, version, path, requestLine;
    private HashMap<String, String> headers, params;

    public HttpRequest(String request) {
        HttpRequestParser httpRequestParser = new HttpRequestParser(request);
        this.method = httpRequestParser.method;
        this.version = httpRequestParser.version;
        this.path = httpRequestParser.path;
        this.requestLine = httpRequestParser.requestLine;
        this.headers = httpRequestParser.headers;
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

    public HashMap<String, String> headers() {
        return headers;
    }
}
