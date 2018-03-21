package http.handlers.directory;

import http.response.HttpResponse;
import http.status.StatusMessages;

public class InvalidResourceHandler implements IResourceHandler {

    private final HttpResponse httpResponse;

    public InvalidResourceHandler() {
        this.httpResponse = new HttpResponse();
    }

    @Override
    public HttpResponse generateResponse() {
        httpResponse.setStatus("404");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(404).toString());
        return httpResponse;
    }
}
