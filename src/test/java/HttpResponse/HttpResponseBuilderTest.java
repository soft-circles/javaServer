package HttpResponse;

import HttpRequest.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseBuilderTest {
    private HttpRequest httpRequest, httpRequest2;
    @BeforeEach
    void setUp() {
        httpRequest = new HttpRequest(request());
        httpRequest2 = new HttpRequest(validPathRequest());
    }

    @Test
    void build() {
        HttpResponse httpResponse = HttpResponseBuilder.build(httpRequest);
        assertEquals("404", httpResponse.status);
        HttpResponse httpResponse1 = HttpResponseBuilder.build(httpRequest2);
        assertEquals("200", httpResponse1.status);
    }

    private String request() {
        return "GET /path/file.html HTTP/1.0\r\nUser-Agent: HTTPTool/1.0\r\n\r\n";
    }

    private String validPathRequest() {
        return "GET / HTTP/1.0\r\nUser-Agent: HTTPTool/1.0\r\n\r\n";
    }
}