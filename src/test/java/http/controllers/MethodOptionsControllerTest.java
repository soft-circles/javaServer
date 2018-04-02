package http.controllers;

import http.IO.file.InvalidPathException;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.router.Router;
import http.status.InvalidStatusCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MethodOptionsControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() throws InvalidRequestException, InvalidPathException, InvalidStatusCodeException, IOException {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        Router router = new Router();
        MethodOptionsController methodOptionsController = new MethodOptionsController(router);
        router.addRoute("/method_options", Arrays.asList(httpMethod.GET, httpMethod.HEAD, httpMethod.POST), methodOptionsController);
        httpResponse = methodOptionsController.generateResponse(httpRequest);
    }

    @Test
    void generateResponse() {
        assertEquals("200", httpResponse.getStatus());
        assertEquals("GET HEAD POST", httpResponse.getHeaders().get("Allow"));
        assertEquals("OK", httpResponse.getReasonPhrase());
    }

    private String rawRequest() {
        return "OPTIONS /method_options HTTP/1.1\n";
    }
}