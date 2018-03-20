package http.handlers.directory;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

public class InvalidResourceHandler implements IResourceHandler {

    private final HttpRequest httpRequest;
    private final HttpResponse httpResponse;

    public InvalidResourceHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.httpResponse = new HttpResponse();
    }

    @Override
    public HttpResponse generateResponse() {
        httpResponse.setStatus("404");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(404).toString());
        return httpResponse;
    }
}
