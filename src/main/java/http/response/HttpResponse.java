package http.response;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private HashMap<String, String> headers;
    private static final String HTTP_VERSION = "HTTP/2.0";
    private String reasonPhrase;
    private String requestHttpVersion;
    private String requestMethod;
    private String requestUri;
    private String sentSize;
    private String body;
    private String status = "200";

    public HttpResponse(Map<String, String> httpConfig) {
        reasonPhrase = httpConfig.get("reasonPhrase");
        requestHttpVersion = httpConfig.get("requestHttpVersion");
        requestMethod = httpConfig.get("requestMethod");
        requestUri = httpConfig.get("requestUri");
        sentSize = httpConfig.get("sentSize");
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        return statusLine() + "\r\n" + body() + "\r\n";
    }

    private String body() {
        if (body != null) {
            return body;
        } else {
            return "";
        }
    }
    public void addToBody(String string) {
        StringBuilder bodyBuilder = new StringBuilder();
        if (body != null) {
            bodyBuilder.append(body);
        }
        bodyBuilder.append("\n");
        bodyBuilder.append(string);
        this.body = bodyBuilder.toString();
    }

    private String headers() {
        StringBuilder headerBuilder = new StringBuilder();
        if (headers != null) {
            for(Map.Entry<String, String> e: headers.entrySet()){
                headerBuilder.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            }
        }
        return headerBuilder.toString();
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
}
