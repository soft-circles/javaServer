package http.request;

import http.method.httpMethod;

import java.util.HashMap;

public class HttpRequest implements IHttpRequest {
    private httpMethod method;
    private int contentLength;
    private String version, path, requestLine;
    private HashMap<String, String> headers, params;
    private byte[] body;

    public HttpRequest(String request) {
        HttpRequestParser httpRequestParser = new HttpRequestParser(request);
        this.method = httpRequestParser.method;
        this.version = httpRequestParser.version;
        this.path = httpRequestParser.path;
        this.requestLine = httpRequestParser.requestLine;
        this.headers = httpRequestParser.headers;
        this.contentLength = httpRequestParser.contentLength;

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

    public HashMap<String, String> headers() {
        return headers;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }
}
