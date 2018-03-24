package http.handlers.request;

import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidRequestHandlerTest {
    private InvalidRequestHandler invalidRequestHandler;
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws InvalidRequestException {
        HttpRequest httpRequest = new HttpRequest("GET /weird HTTP/1.0\n");
        invalidRequestHandler = new InvalidRequestHandler();
        httpResponse = invalidRequestHandler.returnResponse(httpRequest);
    }

    @Test
    void returnResponse() {
        assertEquals("404", httpResponse.getStatus());
        assertEquals("Not Found", httpResponse.getReasonPhrase());
        assertEquals("Content not found".getBytes().length, httpResponse.getBody().length);
    }
}