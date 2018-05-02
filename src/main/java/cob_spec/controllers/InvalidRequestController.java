package cob_spec.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;

public class InvalidRequestController implements IController {
    public InvalidRequestController() { }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.Not_Found);
        httpResponse.setBody("Content not found".getBytes());
        httpResponse.addHeader("Content-Type", "text/html");
        return httpResponse;
    }
}
