package cob_spec.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;


public class HeadController implements IController {

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        return createResponse();
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.OK);
        httpResponse.setEmptyBody();
        return httpResponse;
    }
}
