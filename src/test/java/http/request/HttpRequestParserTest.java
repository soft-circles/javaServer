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
        assertEquals("GET / HTTP/2.0\r", httpRequestParser.getRequestLine());
        assertEquals(httpMethod.GET, httpRequestParser.getMethod());
        assertEquals("HTTP/2.0\r", httpRequestParser.getVersion());
        assertEquals("/", httpRequestParser.getPath());
    }

    @Test
    void headers() {
        assertTrue(httpRequestParser.getHeaders().containsKey("Accept"));
        assertTrue(httpRequestParser.getHeaders().containsValue("text/html\r"));
        assertTrue(httpRequestParser.getHeaders().containsKey("Connection"));
        assertTrue(httpRequestParser.getHeaders().containsValue("keep-alive\r"));
    }

    private String raw_response() {
        return "GET / HTTP/2.0\r\nAccept: text/html\r\nConnection: keep-alive\r\n\r\n";
    }
}