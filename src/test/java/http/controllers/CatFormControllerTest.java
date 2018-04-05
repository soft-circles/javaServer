package http.controllers;

import http.IO.MockFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.MockRouter;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CatFormControllerTest {

    private static final String GET_REQUEST = "GET /cat-form HTTP/1.1\n";
    private static final String POST_REQUEST = "POST /cat-form HTTP/1.1\n";
    private static final String PUT_REQUEST = "PUT /cat-form HTTP/1.1\n";
    private static final String DELETE_REQUEST = "DELETE /cat-form HTTP/1.1\n";
    private CatFormController catFormController;

    @BeforeEach
    void setUp() {
        MockFileIO mockFileIO = new MockFileIO();
        MockRouter mockRouter = new MockRouter();
        catFormController = new CatFormController(mockRouter, mockFileIO);
    }

    @Test
    void generateResponsePost() throws IOException {
        HttpRequest httpRequest = new HttpRequest(POST_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(Status.Created, httpResponse.getStatus());
    }

    @Test
    void generateResponseGet() throws IOException {
        HttpRequest httpRequest = new HttpRequest(GET_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @Test
    void generateResponseDelete() throws IOException {
        HttpRequest httpRequest = new HttpRequest(DELETE_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @Test
    void generateResponsePut() throws IOException {
        HttpRequest httpRequest = new HttpRequest(PUT_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(Status.OK, httpResponse.getStatus());
    }
}