package http.handlers.request;

import http.IO.file.FileIO;
import http.IO.file.IFileIO;
import http.IO.file.InvalidPathException;
import http.controllers.DeleteFileController;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.router.Router;
import http.status.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFileControllerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException, InvalidPathException {
        IFileIO IFileIO = new FileIO("./public");
        Router router = new Router();
        router.addRoute("/form", httpMethod.DELETE, new DeleteFileController(router, IFileIO));
        httpResponse = new DeleteFileController(router, IFileIO).generateResponse(deleteRequest());
    }

    @Test
    void returnResponse() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @AfterEach
    void tearDown() throws IOException {
        Path path = Paths.get("./public/form");
        Files.write(path, "".getBytes());
    }

    private HttpRequest deleteRequest() {
        return new HttpRequest("DELETE /form HTTP/1.1\r\n");
    }
}