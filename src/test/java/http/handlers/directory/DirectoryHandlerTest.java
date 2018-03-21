package http.handlers.directory;

import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryHandlerTest {
    private HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException {
        FileIO fileIO = new FileIO("./public");
        HttpRequest httpRequest = httpRequest();
        httpResponse = new DirectoryHandler(httpRequest, fileIO).generateResponse();
    }

    private HttpRequest httpRequest() {
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
        assertTrue(!httpResponse.getBody().isEmpty());
        assertTrue(!httpResponse.getHeaders().isEmpty());
    }
}