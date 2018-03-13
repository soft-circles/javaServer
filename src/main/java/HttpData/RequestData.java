package HttpData;

import java.util.HashMap;

public class RequestData {
    public HashMap<String, String> options;
    public RequestData(String raw_request) {
        build(raw_request);
    }

    private void build(String raw_request) {
        setStartLine(raw_request);
        setHeaders(raw_request);
        setBody(raw_request);
    }

    private void setBody(String raw_request) {
    }

    private void setHeaders(String raw_request) {
    }

    private void setStartLine(String raw_request) {
    }
}
