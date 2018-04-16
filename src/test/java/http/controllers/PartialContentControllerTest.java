package http.controllers;

import http.IO.FileIO;
import http.IO.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartialContentControllerTest {

    private static final String STATUS_MESSAGE = "Partial Content";
    private static final int SIZE = 5;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private IFileIO IFileIO;
    private HttpRequest httpRequestOutOfRange;

    @BeforeEach
    void setUp() throws IOException {
        httpRequestOutOfRange = new HttpRequest(rawRequestOutOfRange());
        httpRequest = new HttpRequest(rawRequest());
        IFileIO = new FileIO("./public");
        httpResponse = new PartialContentController(IFileIO).generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /partial_content.txt HTTP/1.1\n" +
                "Range: bytes=0-4";
    }

    private String rawRequestOutOfRange() {
        return "GET /partial_content.txt HTTP/1.1\n" +
                "Range: bytes=1000-1001";
    }

    @Test
    void generateResponse416() throws IOException {
        httpResponse = new PartialContentController(IFileIO).generateResponse(httpRequestOutOfRange);
        assertEquals(Status.Range_Not_Satisfiable, httpResponse.getStatus());
    }

    @Test
    void generateResponse() {
        assertEquals(Status.Partial_Content, httpResponse.getStatus());
        assertEquals(SIZE, httpResponse.getBody().length);
    }
}