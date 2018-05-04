package http.response;

import http.handlers.cookie.Cookie;
import http.method.HttpMethod;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponseTest {

    public static final String HTTP_VERSION = "HTTP/1.1";
    public static final String STATUS_LINE = HTTP_VERSION + " " + Status.Forbidden.getCode() + " " + Status.Forbidden.getString();
    public static final String URI = "/";
    public static final String SENT_SIZE = "0";
    public static final String BODY = "<html><body><h1>It works!</h1></body></html>";
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
        assertEquals(HTTP_VERSION, httpResponse.getHttpVersion());
        assertEquals(Status.Forbidden.getString(), httpResponse.getReasonPhrase());
        assertEquals(HTTP_VERSION, httpResponse.getRequestHttpVersion());
        assertEquals(HttpMethod.GET, httpResponse.getRequestMethod());
        assertEquals(URI, httpResponse.getRequestUri());
        assertEquals(SENT_SIZE, httpResponse.getSentSize());
        assertEquals(Status.Forbidden.getCode(), httpResponse.getCode());
    }

    @Test
    void status_line() {
        assertEquals(STATUS_LINE, httpResponse.statusLine());
    }

    @Test
    void addsStringsToBodyAndConvertsItToBytes() {
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
        httpResponse.setBody(BODY.getBytes());
    }

    @Test
    void setEmptyBody() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setEmptyBody();
        assertEquals("", httpResponse.getBodyAsString());
    }

    private void assignAttributes() {
        httpResponse.setStatus(Status.Forbidden);
        httpResponse.setRequestHttpVersion(HTTP_VERSION);
        httpResponse.setRequestMethod(HttpMethod.GET);
        httpResponse.setRequestUri(URI);
        httpResponse.setSentSize(SENT_SIZE);
    }
}