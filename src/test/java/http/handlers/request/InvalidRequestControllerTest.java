package http.handlers.request;

import http.controllers.InvalidRequestController;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class InvalidRequestControllerTest {
    private InvalidRequestController invalidRequestHandler;
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest("GET /weird HTTP/1.0\n");
        invalidRequestHandler = new InvalidRequestController();
        httpResponse = invalidRequestHandler.generateResponse(httpRequest);
    }

    @Test
    void returnResponse() {
        assertEquals(Status.Not_Found, httpResponse.getStatus());
        assertEquals("Content not found".getBytes().length, httpResponse.getBody().length);
    }
}