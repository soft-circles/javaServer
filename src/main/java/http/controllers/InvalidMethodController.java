package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;


public class InvalidMethodController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.Method_Not_Allowed);
        httpResponse.addToBody("");
        httpResponse.addHeader("Content-Type", "text/html");
        return httpResponse;
    }
}
