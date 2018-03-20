package http.handlers.request;

import http.handlers.directory.InvalidResourceHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

public class PutRequestHandler implements IRequestHandler {
    private final HttpRequest httpRequest;
    private final InvalidResourceHandler invalidResourceHandler;

    public PutRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.invalidResourceHandler = new InvalidResourceHandler(httpRequest);
    }

    @Override
    public HttpResponse returnResponse() {
        if (PathChecker.validRoute(httpRequest.path())) {
            return createResponse();
        } else  {
            return invalidResourceHandler.generateResponse();
        }
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
