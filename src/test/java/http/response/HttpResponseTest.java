package http.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpResponseTest {

    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() {
        httpResponse = new HttpResponse();
        assignAttributes();
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
        assertEquals(dummy_request_uri(), httpResponse.getRequestUri());
        assertEquals(dummy_sent_size(), httpResponse.getSentSize());
        assertEquals(dummy_status(), httpResponse.getStatus());
    }

    @Test
    void status_line() {
        assertEquals(dummy_status_line(), httpResponse.statusLine());
    }

    @Test
    void addToBody() {
        httpResponse.setBody("TEXT".getBytes());
        httpResponse.addToBody("More text");
        assertEquals(new String("TEXT\nMore text".getBytes(), StandardCharsets.UTF_8), new String(httpResponse.getBody(), StandardCharsets.UTF_8));
    }

    @Test
    void full_response() {
        setUpForFullResponse();
    }

    private void setUpForFullResponse() {
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase("OK");
        httpResponse.setBody("<html><body><h1>It works!</h1></body></html>".getBytes());
    }

    private void assignAttributes() {
        httpResponse.setStatus(dummy_status());
        httpResponse.setReasonPhrase(dummy_reason());
        httpResponse.setRequestHttpVersion(dummy_request_version());
        httpResponse.setRequestMethod(dummmy_request_method());
        httpResponse.setRequestUri(dummy_request_uri());
        httpResponse.setSentSize(dummy_sent_size());
        httpResponse.setStatus(dummy_status());
    }

    private byte[] fullResponse() {
        String response = "HTTP/2.0 200 OK" +
                "\r\n" +
                "Content-Type: text/html" +
                "\r\n\r\n" +
                "<html><body><h1>It works!</h1></body></html>" +
                "\r\n";
        return response.getBytes();
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

    private String dummy_request_uri() {
        return "/";
    }

    private String dummy_sent_size() {
        return "0";
    }

}