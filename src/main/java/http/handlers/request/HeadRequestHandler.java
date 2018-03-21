package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.directory.DirectoryHandler;
import http.handlers.directory.InvalidResourceHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

import java.io.IOException;


public class HeadRequestHandler implements IRequestHandler {
    protected final HttpRequest httpRequest;
    protected final InvalidResourceHandler invalidResourceHandler;

    public HeadRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.invalidResourceHandler = new InvalidResourceHandler(httpRequest);
    }

    @Override
    public HttpResponse returnResponse() throws IOException {
        if (PathChecker.validRoute(httpRequest.path())) {
            return createResponse();
        } else {
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
