package http.controllers;

import http.IO.file.InvalidPathException;
import http.IO.file.MockFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.MockRouter;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CatFormControllerTest {

    private static final String GET_REQUEST = "GET /cat-form HTTP/1.1\n";
    private static final String POST_REQUEST = "POST /cat-form HTTP/1.1\n";
    private static final String PUT_REQUEST = "PUT /cat-form HTTP/1.1\n";
    private static final String STATUS = "200";
    private static final String STATUS_MESSAGE = "OK";
    private static final String STATUS_POST = "201";
    private static final String STATUS_POST_MESSAGE = "Created";
    private static final String DELETE_REQUEST = "DELETE /cat-form HTTP/1.1\n";
    private CatFormController catFormController;

    @BeforeEach
    void setUp() {
        MockFileIO mockFileIO = new MockFileIO();
        MockRouter mockRouter = new MockRouter();
        catFormController = new CatFormController(mockRouter, mockFileIO);
    }

    @Test
    void generateResponsePost() throws InvalidPathException, InvalidStatusCodeException, IOException {
        HttpRequest httpRequest = new HttpRequest(POST_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(STATUS_POST, httpResponse.getStatus());
        assertEquals(STATUS_POST_MESSAGE, httpResponse.getReasonPhrase());
    }

    @Test
    void generateResponseGet() throws InvalidPathException, InvalidStatusCodeException, IOException {
        HttpRequest httpRequest = new HttpRequest(GET_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(STATUS, httpResponse.getStatus());
        assertEquals(STATUS_MESSAGE, httpResponse.getReasonPhrase());
    }

    @Test
    void generateResponseDelete() throws InvalidPathException, InvalidStatusCodeException, IOException {
        HttpRequest httpRequest = new HttpRequest(DELETE_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(STATUS, httpResponse.getStatus());
        assertEquals(STATUS_MESSAGE, httpResponse.getReasonPhrase());
    }

    @Test
    void generateResponsePut() throws InvalidPathException, InvalidStatusCodeException, IOException {
        HttpRequest httpRequest = new HttpRequest(PUT_REQUEST);

        HttpResponse httpResponse = catFormController.generateResponse(httpRequest);

        assertEquals(STATUS, httpResponse.getStatus());
        assertEquals(STATUS_MESSAGE, httpResponse.getReasonPhrase());
    }
}