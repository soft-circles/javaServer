package http.controllers;

import http.IO.file.IFileIO;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

import java.io.IOException;

public class EditFileController implements IController {

    private final FileHandler fileHandler;

    public EditFileController(IFileIO IFileIO) {
        this.fileHandler = new FileHandler(IFileIO);
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        updateFileAtLocation(httpRequest);
        return createResponse();
    }

    private void updateFileAtLocation(HttpRequest httpRequest) throws IOException {
        fileHandler.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.addToBody("");
        return httpResponse;
    }
}
