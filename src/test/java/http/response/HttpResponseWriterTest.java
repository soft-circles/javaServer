package http.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseWriterTest {

    private byte[] byteArray;
    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        httpResponse = createHttpResponse();
        byteArray = new HttpResponseWriter().sendHttpResponse(httpResponse);
    }

    private HttpResponse createHttpResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase("OK");
        httpResponse.addHeader("Content-Type", "html/text");
        httpResponse.addToBody("Test");
        return httpResponse;
    }

    private String dummyResponseString() {
        return "HTTP/1.1 200 OK" + "\r\n" + "Content-Type: html/text" + "\r\n\r\n" + "Test";
    }

    @Test
    void sendHttpResponse() {
        assertTrue(Arrays.equals(byteArray, dummyResponseString().getBytes()));
    }
}