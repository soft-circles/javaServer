package cob_spec;

import cob_spec.controllers.InvalidMethodController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidMethodControllerTest {

    public static final String REQUEST = "GET /invalid HTTP/1.1\n";
    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest(REQUEST);
        httpResponse = new InvalidMethodController().generateResponse(httpRequest);
    }

    @Test
    void responseReturnsA405Status() {
        assertEquals(Status.Method_Not_Allowed, httpResponse.getStatus());
    }
}