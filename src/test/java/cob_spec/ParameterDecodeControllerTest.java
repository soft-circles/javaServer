package cob_spec;

import cob_spec.controllers.ParameterDecodeController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterDecodeControllerTest {

    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        HttpRequest httpRequest = new HttpRequest(rawRequest());
        httpResponse = new ParameterDecodeController().generateResponse(httpRequest);
    }

    private String rawRequest() {
        return "GET /parameters?variable_1=Operators%20%3C&variable_2=stuff HTTP/1.1\n";
    }

    @Test
    void parametersAreCorrectlyParsed() {
        assertEquals("variable_1 = Operators <variable_2 = stuff", httpResponse.getBodyAsString());
    }
}