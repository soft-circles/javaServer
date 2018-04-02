package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;


public class InvalidMethodController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("405");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(405).toString());
        httpResponse.addToBody("");
        httpResponse.addHeader("Content-Type", "text/html");
        return httpResponse;
    }
}
