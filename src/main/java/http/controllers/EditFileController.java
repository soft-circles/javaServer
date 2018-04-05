package http.controllers;

import http.IO.file.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

import java.io.IOException;

public class EditFileController implements IController {

    private final IFileIO fileIO;

    public EditFileController(IFileIO fileIO) {
        this.fileIO = fileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        updateFileAtLocation(httpRequest);
        return createResponse();
    }

    private void updateFileAtLocation(HttpRequest httpRequest) throws IOException {
        fileIO.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.addToBody("");
        return httpResponse;
    }
}
