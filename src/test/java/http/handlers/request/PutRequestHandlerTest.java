package http.handlers.request;

import http.IO.file.FileIO;
import http.IO.file.IFileIO;
import http.controllers.EditFileController;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PutRequestHandlerTest {
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
    void returnResponse() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    private HttpRequest putRequest() {
        return new HttpRequest("PUT /form HTTP/1.1\r\n");
    }

    private byte[] data() {
        return "MyData".getBytes();
    }
}