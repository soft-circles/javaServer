package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

public class InvalidRequestController implements IController {
    public InvalidRequestController() { }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("404");
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        httpResponse.setBody("Content not found".getBytes());
        httpResponse.addHeader("Content-Type", "text/html");
        return httpResponse;
    }
}
