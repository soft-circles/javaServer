package http.handlers.request;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

public class OptionsRequestHandler implements IRequestHandler {
    public OptionsRequestHandler() {
    }

    @Override
    public HttpResponse returnResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
