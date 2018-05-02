package http.Controller;

import cob_spec.controllers.IController;
import http.request.HttpRequest;
import http.response.HttpResponse;

public class MockController implements IController {

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        return null;
    }
}
