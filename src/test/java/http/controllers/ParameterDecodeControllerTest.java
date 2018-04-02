package http.controllers;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.status.InvalidStatusCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ParameterDecodeControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() throws InvalidRequestException, IOException, InvalidPathException, InvalidStatusCodeException {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        httpResponse = new ParameterDecodeController().generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /parameters?variable_1=Operators%20%3C&variable_2=stuff HTTP/1.1\n";
    }

    @Test
    void generateResponse() {
        assertEquals("variable_1 = Operators <variable_2 = stuff", httpResponse.getBodyAsString());
    }
}