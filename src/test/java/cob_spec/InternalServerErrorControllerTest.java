package cob_spec;

import cob_spec.controllers.InternalServerErrorController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InternalServerErrorControllerTest {
    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest("GET / HTTP/1.1\n");
        httpResponse = new InternalServerErrorController().generateResponse(httpRequest);
    }

    @Test
    void responseReturnsA500() {
        assertEquals(Status.Internal_Server_Error, httpResponse.getStatus());
    }

    @Test
    void BodyReturnsInternalServerError() {
        assertTrue("Internal Server Error".equals(httpResponse.getBodyAsString()));
    }
}