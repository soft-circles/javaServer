package cob_spec;

import cob_spec.controllers.EditFileController;
import http.IO.FileIO;
import http.IO.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PutRequestControllerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException {
        IFileIO IFileIO = new FileIO("./public");
        HttpRequest httpRequest = putRequest();
        httpRequest.setBody(data());
        httpResponse = new EditFileController(IFileIO).generateResponse(httpRequest);
    }


    @AfterEach
    void tearDown() throws IOException {
        byte[] bytes = "".getBytes();
        Path file = Paths.get("./public/form");
        Files.write(file, bytes);
    }

    @Test
    void responseReturnsA200Status() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    private HttpRequest putRequest() {
        return new HttpRequest("PUT /form HTTP/1.1\r\n");
    }

    private byte[] data() {
        return "MyData".getBytes();
    }
}