package http.request;

import http.method.httpMethod;
import http.request.error.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {
    private HttpRequest httpRequest;


    @BeforeEach
    void setUp() throws InvalidRequestException {
        httpRequest = new HttpRequest(getRequest());
    }

    private String getRequest() {
        return firstLine() + "Content-Length: 10\n";
    }

    private String firstLine() {
        return "GET /path/file.html HTTP/1.0\r\n";
    }

    @Test
    void requestLine() {
        assertEquals("GET /path/file.html HTTP/1.0\r", httpRequest.requestLine());
    }

    @Test
    void method() {
        assertEquals(httpMethod.GET, httpRequest.method());
    }

    @Test
    void path() {
        assertEquals("/path/file.html", httpRequest.path());
    }

    @Test
    void version() {
        assertEquals("HTTP/1.0\r", httpRequest.version());
    }

    @Test
    void getContentLength() {
        assertEquals(10, httpRequest.getContentLength());
    }

    @Test
    void headers() {
        assertTrue(httpRequest.headers().containsKey("Content-Length"));
    }
}