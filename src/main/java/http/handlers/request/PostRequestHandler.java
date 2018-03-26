package http.handlers.request;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.handlers.directory.InvalidResourceHandler;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

import java.io.IOException;

public class PostRequestHandler implements IRequestHandler {
    private final InvalidResourceHandler invalidResourceHandler;
    private final FileIO fileIO;

    public PostRequestHandler(FileIO fileIO) {
        this.fileIO = fileIO;
        this.invalidResourceHandler = new InvalidResourceHandler();
    }

    @Override
    public HttpResponse returnResponse(HttpRequest httpRequest) throws IOException, InvalidPathException {
        if (PathChecker.validRoute(httpRequest.path()) && PathChecker.writePermitted(httpRequest.path())) {
            return createResponse(httpRequest);
        } else {
            return invalidResourceHandler.generateResponse();
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
