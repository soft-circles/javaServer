package HttpResponse;

import java.util.HashMap;

public class HttpResponse {
    public HashMap<String, String> header;
    public String http_version;
    public String reason_phrase;
    public String request_http_version;
    public String request_method;
    public String request_uri;
    public String sent_size;
    public String status = "200";

    public HttpResponse(HashMap<String, String> httpConfig) {
//        http_version = responseData.options.get("http_version");
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
}
