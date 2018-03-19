package http.handlers.request;

import http.request.HttpRequest;
import http.response.HttpResponse;

public class OptionsRequestHandler implements IRequestHandler {
    public OptionsRequestHandler(HttpRequest httpRequest) {
    }

    @Override
    public HttpResponse returnResponse() {
        return null;
    }
}
