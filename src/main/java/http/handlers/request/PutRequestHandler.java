package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.RouteChecker;

import java.io.IOException;

public class PutRequestHandler implements IRequestHandler {

    private final FileHandler fileHandler;
    private final InvalidRequestHandler invalidRequestHandler;

    public PutRequestHandler(FileIO fileIO) {

        this.fileHandler = new FileHandler(fileIO);
        this.invalidRequestHandler = new InvalidRequestHandler();
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        if (RouteChecker.validRoute(httpRequest.path()) && RouteChecker.updatePermitted(httpRequest.path())) {
            updateFileAtLocation(httpRequest);
            return createResponse();
        } else  {
            return invalidRequestHandler.generateResponse(httpRequest);
        }
    }

    private void updateFileAtLocation(HttpRequest httpRequest) throws IOException {
        fileHandler.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        return httpResponse;
    }
}
