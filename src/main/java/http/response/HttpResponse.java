package http.response;

import http.handlers.cookie.Cookie;
import http.method.HttpMethod;
import http.status.Status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpResponse {
    private Map<String, String> headers;
    private static final String HTTP_VERSION = "HTTP/1.1";
    private String requestHttpVersion;
    private HttpMethod requestMethod;
    private String requestUri;
    private String sentSize;
    private byte[] body;
    private Status status = Status.OK;
    private ArrayList<Cookie> cookies;

    public HttpResponse()
    {
        cookies = new ArrayList<>();
        headers = new HashMap<>();
    }

    public void setEmptyBody() {
        this.body = "".getBytes();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    public String getBodyAsString() {
        return new String(body, StandardCharsets.UTF_8);
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body.getBytes();
    }

    String getHttpVersion() {
        return HTTP_VERSION;
    }

    String getReasonPhrase() {
        return this.status.getString();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    String statusLine() {
        return HTTP_VERSION + " " + getCode() + " " + getReasonPhrase();
    }

    public void addToBody(String string) {
        StringBuilder bodyBuilder = new StringBuilder();
        if (body != null) {
            bodyBuilder.append(new String(body, StandardCharsets.UTF_8));
        }
        bodyBuilder.append(string);
        this.body = bodyBuilder.toString().getBytes();
    }

    public HttpResponse addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }


    String getSentSize() {
        return sentSize;
    }

    void setSentSize(String sentSize) {
        this.sentSize = sentSize;
    }

    String getRequestUri() {
        return requestUri;
    }

    void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    HttpMethod getRequestMethod() {
        return requestMethod;
    }

    void setRequestMethod(HttpMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    String getRequestHttpVersion() {
        return requestHttpVersion;
    }

    void setRequestHttpVersion(String requestHttpVersion) {
        this.requestHttpVersion = requestHttpVersion;
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    ArrayList<Cookie> getCookies() {
        return this.cookies;
    }

    public void setCookieHeader() {
        StringBuilder sb = new StringBuilder();
        Iterator<Cookie> iterator = this.cookies.iterator();
        while (iterator.hasNext()) {
            Cookie cookie = iterator.next();
            sb.append(cookie.getName())
                .append("=")
                .append(cookie.getValue());
            if (iterator.hasNext()) {
                sb.append("; ");
            }
        }
        addHeader("Set-Cookie", sb.toString());
    }

    int getCode() {
        return status.getCode();
    }
}
