package http.handlers.request;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.RouteChecker;

import java.io.IOException;

public class PostRequestHandler implements IRequestHandler {
    private final FileIO fileIO;
    private final InvalidRequestHandler invalidRequestHandler;

    public PostRequestHandler(FileIO fileIO) {
        this.fileIO = fileIO;
        this.invalidRequestHandler = new InvalidRequestHandler();
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException {
        if (RouteChecker.validRoute(httpRequest.path()) && RouteChecker.writePermitted(httpRequest.path())) {
            return createResponse(httpRequest);
        } else {
            return invalidRequestHandler.generateResponse(httpRequest);
        }
    }

    private HttpResponse createResponse(HttpRequest httpRequest) throws IOException, InvalidPathException {
        HttpResponse httpResponse = new HttpResponse();
        if (fileIO.isDirectory(httpRequest.path())) {
            createFileAtDifferentLocation(httpRequest);
            String fileLocation = httpRequest.path() + "/data";
            httpResponse.addHeader("Location", fileLocation);
            httpResponse.setStatus("201");
            httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(201).toString());
        } else {
            createFileAtPathLocation(httpRequest);
            httpResponse.setStatus("200");
            httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        }
        httpResponse.addToBody("");
        return httpResponse;
    }

    private void createFileAtPathLocation(HttpRequest httpRequest) throws IOException, InvalidPathException {
        fileIO.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private void createFileAtDifferentLocation(HttpRequest httpRequest) throws IOException, InvalidPathException {
        String data = "/data";
        fileIO.createFile(httpRequest.path() + data, httpRequest.getBody());
    }
}
