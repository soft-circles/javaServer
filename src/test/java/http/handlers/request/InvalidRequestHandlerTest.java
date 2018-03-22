package http.handlers.request;

import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidRequestHandlerTest {
    private InvalidRequestHandler invalidRequestHandler;
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() {
        invalidRequestHandler = new InvalidRequestHandler();
        httpResponse = invalidRequestHandler.returnResponse();
    }

    @Test
    void returnResponse() {
        assertEquals("404", httpResponse.getStatus());
        assertEquals("Not Found", httpResponse.getReasonPhrase());
        assertEquals("Content not found", httpResponse.getBody());
    }
}