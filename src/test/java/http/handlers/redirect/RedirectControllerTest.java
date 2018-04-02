package http.handlers.redirect;

import http.controllers.RedirectController;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class RedirectControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() throws InvalidRequestException, UnsupportedEncodingException {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        httpResponse = new RedirectController().generateResponse(httpRequest);
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