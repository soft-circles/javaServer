package http.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {
    private HttpRequest httpRequest;


    @BeforeEach
    void setUp(){
        httpRequest = new HttpRequest(getRequest());
    }

    private String getRequest() {
        return firstLine() + "User-Agent: HTTPTool/1.0\r\n";
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
        assertEquals("GET", httpRequest.method());
    }

    @Test
    void path() {
        assertEquals("/path/file.html", httpRequest.path());
    }

    @Test
    void version() {
        assertEquals("HTTP/1.0\r", httpRequest.version());
    }
}