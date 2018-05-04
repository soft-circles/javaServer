package cob_spec;

import cob_spec.controllers.DirectoryController;
import http.IO.FileIO;
import http.IO.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectoryControllerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException {
        IFileIO IFileIO = new FileIO("./public");
        HttpRequest httpRequest = httpRequest();
        httpResponse = new DirectoryController(IFileIO).generateResponse(httpRequest);
    }

    private HttpRequest httpRequest() {
        String rawRequest = "GET / HTTP/1.1\r\n";
        return new HttpRequest(rawRequest);
    }

    @Test
    void hasHeaders() {
        assertTrue(!httpResponse.getHeaders().isEmpty());
    }

    @Test
    void responseReturnsA200() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @Test
    void bodyHasContent() {
        assertTrue(httpResponse.getBody().length > 0);
    }
}