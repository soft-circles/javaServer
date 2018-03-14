package HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    public HashMap<String, String> headers;
    public String http_version;
    public String reason_phrase;
    public String request_http_version;
    public String request_method;
    public String request_uri;
    public String sent_size;
    public String body;
    public String status = "200";

    public HttpResponse(HashMap<String, String> httpConfig) {
        http_version = httpConfig.get("http_version");
        reason_phrase = httpConfig.get("reason_phrase");
        request_http_version = httpConfig.get("request_http_version");
        request_method = httpConfig.get("request_method");
        request_uri = httpConfig.get("request_uri");
        sent_size = httpConfig.get("sent_size");
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status_line() {
        return http_version + " " + status + " " + reason_phrase;
    }

    public String full_response() {
        return status_line() + "\r\n" + body() + "\r\n";
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
                headerBuilder.append(e.getKey() + ": " + e.getValue() + "\n");
            }
        }
        return headerBuilder.toString();
    }
}
