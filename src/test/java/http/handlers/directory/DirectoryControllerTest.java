package http.handlers.directory;

import http.IO.file.FileIO;
import http.IO.file.IFileIO;
import http.controllers.DirectoryController;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.InvalidStatusCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryControllerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException, InvalidRequestException, InvalidStatusCodeException {
        IFileIO IFileIO = new FileIO("./public");
        HttpRequest httpRequest = httpRequest();
        httpResponse = new DirectoryController(IFileIO).generateResponse(httpRequest);
    }

    private HttpRequest httpRequest() throws InvalidRequestException, UnsupportedEncodingException {
        String rawRequest = "GET / HTTP/1.1\\r\\n\" +\n" +
                "Host: www.nowhere123.com\\r\\n\" +\n" +
                "Accept: image/gif, image/jpeg, */*\\r\\n\" +\n" +
                "Accept-Language: en-us\\r\\n\" +\n" +
                "Accept-Encoding: gzip, deflate\\r\\n\" +\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
        return new HttpRequest(rawRequest);
    }

    @Test
    void generateResponse() {
        assertTrue(httpResponse.getBody().length > 0);
        assertTrue(!httpResponse.getHeaders().isEmpty());
    }
}