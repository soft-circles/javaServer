package cob_spec;

import cob_spec.controllers.TeaPotController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeaPotControllerTest {

    TeaPotController teaPotController;

    @BeforeEach
    void setUp() {
        teaPotController = new TeaPotController();
    }

    @Test
    void responseReturns418Status() {
        String rawRequest = "GET /coffee HTTP/1.1\n";
        HttpRequest request = new HttpRequest(rawRequest);

        HttpResponse result = teaPotController.generateResponse(request);

        assertEquals(Status.Im_a_teapot, result.getStatus());

    }

    @Test
    void responseReturns200Status() {

        String rawRequest = "GET /tea HTTP/1.1\n";
        HttpRequest request = new HttpRequest(rawRequest);

        HttpResponse result = teaPotController.generateResponse(request);

        assertEquals(Status.OK, result.getStatus());
    }
}