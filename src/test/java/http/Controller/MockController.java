package http.Controller;

import http.controllers.IController;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public class MockController implements IController {

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        return null;
    }
}
