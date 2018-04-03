package http.controllers;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.InvalidStatusCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class PartialContentControllerTest {

    private static final String STATUS = "206";
    private static final String STATUS_MESSAGE = "Partial Content";
    private static final int SIZE = 4;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private FileIO fileIO;

    @BeforeEach
    void setUp() throws InvalidRequestException, IOException, InvalidPathException, InvalidStatusCodeException {
        httpRequest = new HttpRequest(rawRequest());
        fileIO = new FileIO("./public");
        httpResponse = new PartialContentController(fileIO).generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /partial_content.txt HTTP/1.1\n" +
                "Range: bytes=0-4";
    }

    @Test
    void generateResponse() throws IOException {
        assertEquals(STATUS, httpResponse.getStatus());
        assertEquals(STATUS_MESSAGE, httpResponse.getReasonPhrase());
        assertEquals(SIZE, httpResponse.getBody().length);
    }
}