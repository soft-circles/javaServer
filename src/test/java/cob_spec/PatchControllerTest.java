package cob_spec;

import cob_spec.controllers.PatchController;
import http.IO.FileIO;
import http.IO.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatchControllerTest {

    public static final String CONTENT = "default content";
    private static final String CONTENT_LOCATION = "Content-Location";
    private static final String FILE = "/patch-content.txt";
    private static final String ETAG = "ETag";
    private HttpResponse httpResponse;
    private HttpResponse httpResponsePatch;
    private IFileIO IFileIO;
    private HttpRequest httpRequestPatch;

    @BeforeEach
    void setUp() throws IOException {
        IFileIO = new FileIO("./public");
        httpRequestPatch = new HttpRequest(rawPatchRequest());
        httpRequestPatch.setBody("patch content".getBytes());
        HttpRequest httpRequest = new HttpRequest(rawGetRequest());
        httpResponse = new PatchController(IFileIO).generateResponse(httpRequest);
        httpResponsePatch = new PatchController(IFileIO).generateResponse(httpRequestPatch);
    }

    @AfterEach
    void tearDown() throws IOException {
        IFileIO.createFile("/patch-content.txt", "default content".getBytes());
    }

    private String rawGetRequest() {
        return "GET /patch-content.txt HTTP/1.1\n";
    }

    private String rawPatchRequest() throws IOException {
        return "PATCH /patch-content.txt HTTP/1.1\r\n" +
                "If-Match: " + getSha();
    }

    private String getSha() throws IOException {
        return DigestUtils.shaHex(IFileIO.readFile("/patch-content.txt"));
    }

    @Test
    void responseBodyMatchesContent() throws UnsupportedEncodingException {
        assertEquals(CONTENT, getActual());
    }

    @Test
    void responseGeneratesA200Status() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @Test
    void correctHeadersOnPatchWithEtag() {
        assertTrue(httpResponsePatch.getHeaders().containsKey(CONTENT_LOCATION));
        assertTrue(httpResponsePatch.getHeaders().containsValue(FILE));
        assertTrue(httpResponsePatch.getHeaders().containsKey(ETAG));
    }

    @Test
    void responseReturnsA204 () {
        assertEquals(Status.No_Content, httpResponsePatch.getStatus());
    }

    private String getActual() throws UnsupportedEncodingException {
        return new String(httpResponse.getBody(), "UTF-8");
    }
}