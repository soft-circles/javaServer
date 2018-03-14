package HttpResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseTest {

    private HashMap<String, String> httpConfig;
    private HttpResponse httpResponse;
    private String status;
    @BeforeEach
    void setUp() {
        httpResponse = new HttpResponse(setupHttpConfig());
        httpResponse.setStatus(dummy_status());
    }

    @Test
    void setStatus() {
        assertEquals(dummy_status(), httpResponse.status);
    }

    @Test
    void testAttributes() {
        assertEquals(dummy_version(), httpResponse.http_version);
        assertEquals(dummy_reason(), httpResponse.reason_phrase);
        assertEquals(dummy_request_version(), httpResponse.request_http_version);
        assertEquals(dummmy_request_method(), httpResponse.request_method);
        assertEquals(dummy_requeset_uri(), httpResponse.request_uri);
        assertEquals(dummy_sent_size(), httpResponse.sent_size);
        assertEquals(dummy_status(), httpResponse.status);
    }

    @Test
    void status_line() {
        assertEquals(dummy_status_line(), httpResponse.status_line());
    }

    @Test
    void addToBody() {
        httpResponse.body = "TEXT";
        httpResponse.addToBody("More text");
        assertEquals("TEXT\nMore text", httpResponse.body);
    }

    @Test
    void full_response() {
        setUpForFullResponse();
        assertEquals(fullResponse(), httpResponse.full_response());
    }

    private void setUpForFullResponse() {
        httpResponse.http_version = "HTTP/1.1";
//        httpResponse.headers = giveHeaders();
        httpResponse.status = "200";
        httpResponse.reason_phrase = "OK";
        httpResponse.body = "<html><body><h1>It works!</h1></body></html>";
    }

    private String fullResponse() {
        return "HTTP/1.1 200 OK" +
                "\r\n" +
//                "Content-Type: text/html\n" +
                "<html><body><h1>It works!</h1></body></html>\r\n";
    }

    private HashMap<String, String> giveHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html");
        return headers;
    }

    private HashMap setupHttpConfig() {
        httpConfig = new HashMap<>();
        httpConfig.put("http_version", dummy_version());
        httpConfig.put("reason_phrase", dummy_reason());
        httpConfig.put("request_http_version", dummy_request_version());
        httpConfig.put("request_method", dummmy_request_method());
        httpConfig.put("request_uri", dummy_requeset_uri());
        httpConfig.put("sent_size", dummy_sent_size());
        return httpConfig;
    }

    private String dummy_status_line() {
        return dummy_version() +  " " + dummy_status() + " " + dummy_reason();
    }

    private String dummy_version() {
        return "HTTP/2.0";
    }

    private String dummy_status() {
        return "403";
    }

    private String dummy_reason() {
        return "Forbidden";
    }

    private String dummy_request_version() {
        return "HTTP/1.1";
    }

    private String dummmy_request_method() {
        return "GET";
    }

    private String dummy_requeset_uri() {
        return "/";
    }

    private String dummy_sent_size() {
        return "0";
    }

}