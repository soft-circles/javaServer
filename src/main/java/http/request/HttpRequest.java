package http.request;

import http.method.httpMethod;
import http.request.error.InvalidRequestException;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest implements IHttpRequest {
    private httpMethod method;
    private int contentLength;
    private String version;
    private String path;
    private String requestLine;
    private Map<String, String> headers;
    private byte[] body;

    public HttpRequest(String request) throws InvalidRequestException {
        HttpRequestParser httpRequestParser = new HttpRequestParser(request);
        this.method = httpRequestParser.getMethod();
        this.version = httpRequestParser.getVersion();
        this.path = httpRequestParser.getPath();
        this.requestLine = httpRequestParser.getRequestLine();
        this.headers = httpRequestParser.getHeaders();
        this.contentLength = httpRequestParser.getContentLength();
    }

    @Override
    public String requestLine() {
        return requestLine;
    }

    @Override
    public httpMethod method() {
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
}
