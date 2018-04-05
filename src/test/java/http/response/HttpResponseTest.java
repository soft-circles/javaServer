package http.response;

import http.handlers.cookie.Cookie;
import http.method.httpMethod;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpResponseTest {

    private HttpResponse httpResponse;
    public static final String NAME = "testName";
    public static final String VALUE = "testValue";
    private Cookie cookie;

    @BeforeEach
    void setUp() {
        httpResponse = new HttpResponse();
        cookie = new Cookie(NAME, VALUE);
        assignAttributes();
    }

    @Test
    void setStatus() {
        assertEquals(Status.Forbidden, httpResponse.getStatus());
    }

    @Test
    void testAttributes() {
        assertEquals(dummy_version(), httpResponse.getHttpVersion());
        assertEquals(dummy_reason(), httpResponse.getReasonPhrase());
        assertEquals(dummy_request_version(), httpResponse.getRequestHttpVersion());
        assertEquals(httpMethod.GET, httpResponse.getRequestMethod());
        assertEquals(dummy_request_uri(), httpResponse.getRequestUri());
        assertEquals(dummy_sent_size(), httpResponse.getSentSize());
        assertEquals(Status.Forbidden.getCode(), httpResponse.getCode());
    }

    @Test
    void status_line() {
        assertEquals(dummy_status_line(), httpResponse.statusLine());
    }

    @Test
    void addToBody() {
        httpResponse.setBody("TEXT".getBytes());
        httpResponse.addToBody("More text");
        assertEquals(new String("TEXTMore text".getBytes(), StandardCharsets.UTF_8), new String(httpResponse.getBody(), StandardCharsets.UTF_8));
    }

    @Test
    void returnsCookies() {
        httpResponse.addCookie(cookie);
        httpResponse.addCookie(cookie);
        assertEquals(2, httpResponse.getCookies().size());
    }

    @Test
    void full_response() {
        setUpForFullResponse();
    }

    private void setUpForFullResponse() {
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.setStatus(Status.OK);
        httpResponse.setBody("<html><body><h1>It works!</h1></body></html>".getBytes());
    }

    private void assignAttributes() {
        httpResponse.setStatus(Status.Forbidden);
        httpResponse.setRequestHttpVersion(dummy_request_version());
        httpResponse.setRequestMethod(httpMethod.GET);
        httpResponse.setRequestUri(dummy_request_uri());
        httpResponse.setSentSize(dummy_sent_size());
    }

    private String dummy_status_line() {
        return dummy_version() +  " " + dummy_status() + " " + dummy_reason();
    }

    private String dummy_version() {
        return "HTTP/1.1";
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

    private String dummy_request_uri() {
        return "/";
    }

    private String dummy_sent_size() {
        return "0";
    }

}