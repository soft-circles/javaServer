package http.Controller;

import http.controllers.IController;
import http.request.HttpRequest;
import http.response.HttpResponse;

public class MockController implements IController {

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        return null;
    }
}
