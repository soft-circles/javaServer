package http.request;

import http.method.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpRequestParserTest {
    private HttpRequestParser httpRequestParser;
    @BeforeEach
    void setUp() {
        httpRequestParser = new HttpRequestParser(rawRequest());
    }

    @Test
    void firstLineAttributes() {
        assertEquals("GET / HTTP/2.0\r", httpRequestParser.getRequestLine());
        assertEquals(HttpMethod.GET, httpRequestParser.getMethod());
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

    @Test
    void getCookies() {
        assertEquals(1, httpRequestParser.getCookies().size());
    }

    private String rawRequest() {
        return "GET / HTTP/2.0\r\nAccept: text/html\r\nCookie: test=cookie\r\nConnection: keep-alive\r\n\r\n";
    }
}