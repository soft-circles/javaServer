package http.handlers.request;

import http.handlers.directory.InvalidResourceHandler;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

public class DeleteRequestHandler implements IRequestHandler {
    private final HttpRequest httpRequest;
    private final InvalidResourceHandler invalidResourceHandler;
    private final FileHandler fileHandler;

    public DeleteRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.invalidResourceHandler = new InvalidResourceHandler(httpRequest);
        this.fileHandler = new FileHandler();
    }

    @Override
    public HttpResponse returnResponse() {
        if (PathChecker.validRoute(httpRequest.path()) && PathChecker.deletePermitted(httpRequest.path()) && deleted(httpRequest.path())) {
            return createResponse();
        } else  {
            return invalidResourceHandler.generateResponse();
        }
    }

    public boolean deleted(String path) {
        return fileHandler.deleteFile(path);
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
