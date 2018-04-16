package http.handlers.directory;

import http.IO.FileIO;
import http.IO.IFileIO;
import http.controllers.DirectoryController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
        String rawRequest = "GET / HTTP/1.1\r\\n\" +\n";
        return new HttpRequest(rawRequest);
    }

    @Test
    void generateResponse() {
        assertTrue(httpResponse.getBody().length > 0);
        assertTrue(!httpResponse.getHeaders().isEmpty());
    }
}