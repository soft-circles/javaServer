package http.request;

import http.handlers.cookie.Cookie;
import http.method.HttpMethod;

import java.util.ArrayList;
import java.util.Map;

public class HttpRequest implements IHttpRequest {
    private final Map<String, String> parameters;
    private HttpMethod method;
    private int contentLength;
    private String version;
    private String path;
    private String requestLine;
    private Map<String, String> headers;
    private byte[] body;
    private ArrayList<Cookie> cookies;

    public HttpRequest(String request) {
        HttpRequestParser httpRequestParser = new HttpRequestParser(request);
        this.method = httpRequestParser.getMethod();
        this.version = httpRequestParser.getVersion();
        this.path = httpRequestParser.getPath();
        this.requestLine = httpRequestParser.getRequestLine();
        this.headers = httpRequestParser.getHeaders();
        this.contentLength = httpRequestParser.getContentLength();
        this.parameters = httpRequestParser.getParameters();
        this.cookies = httpRequestParser.getCookies();
    }

    @Override
    public String requestLine() {
        return requestLine;
    }

    @Override
    public HttpMethod method() {
        return method;
    }

    @Override
    public String path()
    {
        return path;
    }

    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String version() {
        return version;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public ArrayList<Cookie> getCookies() {
        return this.cookies;
    }

    public String[] getPartialRange() {
        String range = headers.get("Range");
        return range.substring("bytes=".length()).split("-");
    }
}
