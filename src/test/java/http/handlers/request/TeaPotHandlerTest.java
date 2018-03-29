package http.handlers.request;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TeaPotHandlerTest {

    TeaPotHandler handlerUnderTest;

    @BeforeEach
    void setUp() {
        handlerUnderTest = new TeaPotHandler();
    }

    @Test
    void returns418code() throws InvalidRequestException, IOException, InvalidPathException {
        String rawRequest = "GET /coffee HTTP/1.1 \r\n\r\n";
        HttpRequest request = new HttpRequest(rawRequest);

        HttpResponse result = handlerUnderTest.generateResponse(request);

        assertEquals("418", result.getStatus());

    }

    @Test
    void returns200code() throws InvalidRequestException, IOException, InvalidPathException {

        String rawRequest = "GET /tea HTTP/1.1 \r\n\r\n";
        HttpRequest request = new HttpRequest(rawRequest);

        HttpResponse result = handlerUnderTest.generateResponse(request);

        assertEquals("200", result.getStatus());
    }
}