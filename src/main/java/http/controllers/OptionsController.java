package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

public class OptionsController implements IController {
    public OptionsController() {
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.addToBody("");
        return httpResponse;
    }
}
