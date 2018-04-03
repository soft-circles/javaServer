package http.controllers;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.InvalidStatusCodeException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Patch;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class PatchControllerTest {

    public static final String CONTENT = "default content";
    private static final String STATUS = "200";
    private static final String MESSAGE = "OK";
    private static final String STATUS_PATCH = "204";
    private static final String MESSAGE_PATCH = "No Content";
    private static final String CONTENT_LOCATION = "Content-Location";
    private static final String FILE = "/patch-content.txt";
    private static final String ETAG = "ETag";
    private HttpResponse httpResponse;
    private HttpResponse httpResponsePatch;
    private FileIO fileIO;
    private HttpRequest httpRequestPatch;

    @BeforeEach
    void setUp() throws InvalidRequestException, IOException, InvalidPathException, InvalidStatusCodeException {
        fileIO = new FileIO("./public");
        httpRequestPatch = new HttpRequest(rawPatchRequest());
        httpRequestPatch.setBody("patch content".getBytes());
        HttpRequest httpRequest = new HttpRequest(rawGetRequest());
        httpResponse = new PatchController(fileIO).generateResponse(httpRequest);
        httpResponsePatch = new PatchController(fileIO).generateResponse(httpRequestPatch);
    }

    @AfterEach
    void tearDown() throws IOException, InvalidPathException {
        fileIO.createFile("/patch-content.txt", "default content".getBytes());
    }

    private String rawGetRequest() {
        return "GET /patch-content.txt HTTP/1.1\n";
    }

    private String rawPatchRequest() throws IOException {
        return "PATCH /patch-content.txt HTTP/1.1\r\n" +
                "If-Match: " + getSha();
    }

    private String getSha() throws IOException {
        return DigestUtils.shaHex(fileIO.readFile("/patch-content.txt"));
    }

    @Test
    void generateResponse() throws UnsupportedEncodingException {
        assertEquals(MESSAGE, httpResponse.getReasonPhrase());
        assertEquals(STATUS, httpResponse.getStatus());
        assertEquals(CONTENT, getActual());
    }

    @Test
    void generateResponsePatch () {
        assertEquals(STATUS_PATCH, httpResponsePatch.getStatus());
        assertEquals(MESSAGE_PATCH, httpResponsePatch.getReasonPhrase());
        assertTrue(httpResponsePatch.getHeaders().containsKey(CONTENT_LOCATION));
        assertTrue(httpResponsePatch.getHeaders().containsValue(FILE));
        assertTrue(httpResponsePatch.getHeaders().containsKey(ETAG));
    }

    private String getActual() throws UnsupportedEncodingException {
        return new String(httpResponse.getBody(), "UTF-8");
    }
}