package cob_spec;

import cob_spec.controllers.RedirectController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedirectControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        httpResponse = new RedirectController().generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /redirect HTTP/1.0\n";
    }

    @Test
    void responseReturnsA302Status() {
        assertEquals(Status.Found, httpResponse.getStatus());
    }

    @Test
    void responseHasCorrectHeaders() {
        assertTrue(httpResponse.getHeaders().containsKey("Location"));
        assertTrue(httpResponse.getHeaders().containsValue("/"));
    }
}