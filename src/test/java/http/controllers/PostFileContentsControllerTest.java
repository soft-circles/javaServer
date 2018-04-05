package http.controllers;

import http.IO.IFileIO;
import http.IO.MockFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PostFileContentsControllerTest {

    public static final String REQUEST_FILE = "POST /testing.txt HTTP/1.1\n";
    public static final String REQUEST_DIRECTORY = "POST /directory HTTP/1.1\n";
    public static final String FILE_LOCATION = "/directory/data";

    private HttpResponse httpResponseFile;
    private HttpResponse httpResponseDirectory;

    @BeforeEach
    void setUp() throws IOException {
        IFileIO IFileIO = new MockFileIO();
        HttpRequest httpRequest = new HttpRequest(REQUEST_FILE);
        HttpRequest httpRequest1 = new HttpRequest(REQUEST_DIRECTORY);
        httpResponseFile = new PostFileContentsController(IFileIO).generateResponse(httpRequest);
        httpResponseDirectory = new PostFileContentsController(IFileIO).generateResponse(httpRequest1);
    }

    @Test
    void generateResponseForDirectory() {
        assertEquals(FILE_LOCATION, httpResponseDirectory.getHeaders().get("Location"));
        assertEquals(Status.Created, httpResponseDirectory.getStatus());
    }

    @Test
    void generateResponseForFile() {
        assertEquals(Status.OK, httpResponseFile.getStatus());
    }
}