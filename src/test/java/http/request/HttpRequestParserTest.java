package http.request;

import http.method.httpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestParserTest {
    private HttpRequestParser httpRequestParser;
    @BeforeEach
    void setUp() {
        httpRequestParser = new HttpRequestParser(raw_response());
    }

    @Test
    void firstLineAttributes() {
        assertEquals("GET / HTTP/2.0\r", httpRequestParser.requestLine);
        assertEquals(httpMethod.GET, httpRequestParser.method);
        assertEquals("HTTP/2.0\r", httpRequestParser.version);
        assertEquals("/", httpRequestParser.path);
    }

    @Test
    void headers() {
        assertTrue(httpRequestParser.headers.containsKey("Accept"));
        assertTrue(httpRequestParser.headers.containsValue("text/html\r"));
        assertTrue(httpRequestParser.headers.containsKey("Connection"));
        assertTrue(httpRequestParser.headers.containsValue("keep-alive\r"));
    }

    private String raw_response() {
        return "GET / HTTP/2.0\r\nAccept: text/html\r\nConnection: keep-alive\r\n\r\n";
    }
}