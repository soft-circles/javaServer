package http.handlers.request;

import http.controllers.HeadController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        httpResponse = new HeadController().generateResponse(httpRequest());
    }

    private HttpRequest httpRequest() {
        return new HttpRequest("HEAD / HTTP/1.1\r\n");
    }

    @Test
    void returnResponse() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }
}