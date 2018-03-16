package http.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponseTest {

    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() {
        httpResponse = new HttpResponse(setupHttpConfig());
        httpResponse.setStatus(dummy_status());
    }

    @Test
    void setStatus() {
        assertEquals(dummy_status(), httpResponse.getStatus());
    }

    @Test
    void testAttributes() {
        assertEquals(dummy_version(), httpResponse.getHttpVersion());
        assertEquals(dummy_reason(), httpResponse.getReasonPhrase());
        assertEquals(dummy_request_version(), httpResponse.getRequestHttpVersion());
        assertEquals(dummmy_request_method(), httpResponse.getRequestMethod());
        assertEquals(dummy_requeset_uri(), httpResponse.getRequestUri());
        assertEquals(dummy_sent_size(), httpResponse.getSentSize());
        assertEquals(dummy_status(), httpResponse.getStatus());
    }

    @Test
    void status_line() {
        assertEquals(dummy_status_line(), httpResponse.statusLine());
    }

    @Test
    void addToBody() {
        httpResponse.setBody("TEXT");
        httpResponse.addToBody("More text");
        assertEquals("TEXT\nMore text", httpResponse.getBody());
    }

    @Test
    void full_response() {
        setUpForFullResponse();
        assertEquals(fullResponse(), httpResponse.fullResponse());
    }

    private void setUpForFullResponse() {
//        httpResponse.headers = giveHeaders();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase("OK");
        httpResponse.setBody("<html><body><h1>It works!</h1></body></html>");
    }

    private String fullResponse() {
        return "HTTP/2.0 200 OK" +
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
        HashMap<String, String> httpConfig = new HashMap<>();
        httpConfig.put("httpVersion", dummy_version());
        httpConfig.put("reasonPhrase", dummy_reason());
        httpConfig.put("requestHttpVersion", dummy_request_version());
        httpConfig.put("requestMethod", dummmy_request_method());
        httpConfig.put("requestUri", dummy_requeset_uri());
        httpConfig.put("sentSize", dummy_sent_size());
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