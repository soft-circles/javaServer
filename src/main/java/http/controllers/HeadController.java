package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;


public class HeadController implements IController {

    public HeadController() {
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        return createResponse();
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.addToBody("");
        return httpResponse;
    }
}
