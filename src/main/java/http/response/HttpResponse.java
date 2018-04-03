package http.response;

import http.handlers.cookie.Cookie;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpResponse {
    private Map<String, String> headers;
    private static final String HTTP_VERSION = "HTTP/1.1";
    private String reasonPhrase;
    private String requestHttpVersion;
    private String requestMethod;
    private String requestUri;
    private String sentSize;
    private byte[] body;
    private String status = "200";
    private ArrayList<Cookie> cookies;

    public HttpResponse()
    {
        cookies = new ArrayList<>();
        headers = new HashMap<>();
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

    public String getHttpVersion() {
        return HTTP_VERSION;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String statusLine() {
        return HTTP_VERSION + " " + status + " " + reasonPhrase;
    }

    public String fullResponse() {
        return statusLine() + "\r\n" + headers() + "\r\n\r\n" + body();
    }

    private byte[] body() {
        if (body != null) {
            return body;
        } else {
            return new byte[0];
        }
    }
    public void addToBody(String string) {
        StringBuilder bodyBuilder = new StringBuilder();
        if (body != null) {
            bodyBuilder.append(new String(body, StandardCharsets.UTF_8));
        }
        bodyBuilder.append(string);
        this.body = bodyBuilder.toString().getBytes();
    }

    private String headers() {
        StringBuilder headerBuilder = new StringBuilder();
        if (headers != null) {
            for(Map.Entry<String, String> e: headers.entrySet()){
                headerBuilder.append(e.getKey()).append(": ").append(e.getValue());
            }
        }
        return headerBuilder.toString();
    }

    public HttpResponse addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }


    public String getSentSize() {
        return sentSize;
    }

    public void setSentSize(String sentSize) {
        this.sentSize = sentSize;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestHttpVersion() {
        return requestHttpVersion;
    }

    public void setRequestHttpVersion(String requestHttpVersion) {
        this.requestHttpVersion = requestHttpVersion;
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public ArrayList<Cookie> getCookies() {
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
}
