package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.directory.InvalidResourceHandler;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

import java.io.IOException;

public class PostRequestHandler implements IRequestHandler {
    private final HttpRequest httpRequest;
    private final InvalidResourceHandler invalidResourceHandler;
    private final FileHandler fileHandler;

    public PostRequestHandler(HttpRequest httpRequest, FileIO fileIO) {
        this.httpRequest = httpRequest;
        this.fileHandler = new FileHandler(fileIO);
        this.invalidResourceHandler = new InvalidResourceHandler(httpRequest);
    }

    @Override
    public HttpResponse returnResponse() throws IOException {
        if (PathChecker.validRoute(httpRequest.path()) && PathChecker.writePermitted(httpRequest.path())) {
            createFileAtLocation();
            return createResponse();
        } else {
            return invalidResourceHandler.generateResponse();
        }
    }

    private void createFileAtLocation() throws IOException {
        fileHandler.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
