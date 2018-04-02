package http.controllers;

import http.controllers.IController;
import http.controllers.InvalidRequestController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.InvalidStatusCodeException;


public class TeaPotController implements IController {
    private HttpResponse handleGet(HttpRequest httpRequest) {
        HttpResponse response = new HttpResponse();
        if (httpRequest.path().equals("/coffee")) {
            response.setStatus("418");
            response.setBody("I'm a teapot");
        } else {
            response.setStatus("200");
            response.setBody("");
        }
        return response;
    }

    public HttpResponse generateResponse(HttpRequest httpRequest) throws InvalidStatusCodeException {
        switch (httpRequest.method()) {
            case GET:
                return handleGet(httpRequest);
            default:
                return new InvalidRequestController().generateResponse(httpRequest);
        }
    }
}
