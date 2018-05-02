package cob_spec;

import cob_spec.controllers.InvalidRequestController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidRequestControllerTest {
    private InvalidRequestController invalidRequestController;
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest("GET /weird HTTP/1.0\n");
        invalidRequestController = new InvalidRequestController();
        httpResponse = invalidRequestController.generateResponse(httpRequest);
    }

    @Test
    void responseReturnsA404Status() {
        assertEquals(Status.Not_Found, httpResponse.getStatus());
    }

    @Test
    void bodyContainsContentNotFound() {
        assertEquals("Content not found".getBytes().length, httpResponse.getBody().length);
    }
}