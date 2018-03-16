package http.handlers;

import http.handlers.response.ResponseHandler;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseHandlerTest {
    private String rawRequest;
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() {
        rawRequest = rawRequest();
        httpResponse = ResponseHandler.buildResponse(rawRequest);
    }

    @Test
    void buildResponse() {
        assertEquals("HTTP/2.0 200 OK", httpResponse.statusLine());
    }

    private String rawRequest() {
        return "GET / HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
    }
}