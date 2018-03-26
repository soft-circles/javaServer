package http.handlers.redirect;

import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedirectHandlerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() throws InvalidRequestException {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        httpResponse = new RedirectHandler().generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /redirect HTTP/1.0\n";
    }

    @Test
    void generateResponse() {
        assertEquals("302", httpResponse.getStatus());
        assertEquals("Found", httpResponse.getReasonPhrase());
        assertTrue(httpResponse.getHeaders().containsKey("Location"));
        assertTrue(httpResponse.getHeaders().containsValue("/"));
    }
}