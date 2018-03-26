package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.RouteChecker;

import java.io.IOException;

public class DeleteRequestHandler implements IRequestHandler {

    private final FileHandler fileHandler;
    private final InvalidRequestHandler invalidRequestHandler;

    public DeleteRequestHandler(FileIO fileIO) {
        this.invalidRequestHandler = new InvalidRequestHandler();
        this.fileHandler = new FileHandler(fileIO);
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        if (RouteChecker.validRoute(httpRequest.path()) && RouteChecker.deletePermitted(httpRequest.path()) && deleted(httpRequest.path())) {
            return createResponse();
        } else  {
            return invalidRequestHandler.generateResponse(httpRequest);
        }
    }

    public boolean deleted(String path) throws IOException {
        return fileHandler.deleteFile(path);
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
