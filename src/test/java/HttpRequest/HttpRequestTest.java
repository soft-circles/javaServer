package HttpRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {
    private HttpRequest httpRequest;


    @BeforeEach
    void setUp() throws IOException {
        BufferedReader requestLine = new BufferedReader(new StringReader(getRequest()));
        httpRequest = new HttpRequest(requestLine);
    }

    private String getRequest() {
        return "GET / HTTP/2.0";
    }

    @Test
    void requestLine() {
        assertEquals(getRequest(), httpRequest.requestLine());
    }

    @Test
    void method() {
        assertEquals("GET", httpRequest.method());
    }

    @Test
    void path() {
        assertEquals("t", httpRequest.path());
    }

    @Test
    void version() {
        assertEquals("HTTP/2.0", httpRequest.version());
    }
}