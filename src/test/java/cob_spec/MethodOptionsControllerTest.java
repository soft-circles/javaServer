package cob_spec;

import cob_spec.controllers.MethodOptionsController;
import http.method.HttpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Router;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodOptionsControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        Router router = new Router();
        MethodOptionsController methodOptionsController = new MethodOptionsController(router);
        router.addRoute("/method_options", Arrays.asList(HttpMethod.GET, HttpMethod.HEAD, HttpMethod.POST), methodOptionsController);
        httpResponse = methodOptionsController.generateResponse(httpRequest);
    }

    @Test
    void headersIncludeMethods() {
        assertEquals("GET, HEAD, POST", httpResponse.getHeaders().get("Allow"));
    }

    @Test
    void responseReturns200() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    private String rawRequest() {
        return "OPTIONS /method_options HTTP/1.1\n";
    }
}