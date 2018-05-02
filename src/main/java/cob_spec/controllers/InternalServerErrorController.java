package cob_spec.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;

public class InternalServerErrorController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.Internal_Server_Error);
        httpResponse.setBody("Internal Server Error");
        return httpResponse;
    }
}
