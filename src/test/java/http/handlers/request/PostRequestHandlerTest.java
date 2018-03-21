package http.handlers.request;

import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestHandlerTest {
    HttpResponse httpResponse;
    @BeforeEach
    void setUp() throws IOException {
        FileIO fileIO = new FileIO("./public");
        HttpRequest httpRequest = postRequest();
        httpRequest.setBody(data());
        httpResponse = new PostRequestHandler(httpRequest, fileIO).returnResponse();
    }

    @AfterEach
    void tearDown() throws IOException {
        Path path = Paths.get("../cob_spec/public/form");
        Files.write(path, "".getBytes());
    }

    @Test
    void returnResponse() {
        assertEquals("200", httpResponse.getStatus());
        assertEquals("OK", httpResponse.getReasonPhrase());
    }

    private HttpRequest postRequest() {
        String rawRequest = "POST /form HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
        return new HttpRequest(rawRequest);
    }

    private byte[] data() {
        return "MyData".getBytes();
    }
}