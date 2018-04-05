package http.controllers;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Router;
import http.status.StatusMessages;

import java.io.IOException;

public class PostFileContentsController implements IController {
    private final FileIO fileIO;
    private final Router router;

    public PostFileContentsController(Router router, FileIO fileIO) {
        this.router = router;
        this.fileIO = fileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
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
    private void createFileAtPathLocation(HttpRequest httpRequest) throws IOException {
        fileIO.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private void createFileAtDifferentLocation(HttpRequest httpRequest) throws IOException {
        String data = "/data";
        fileIO.createFile(httpRequest.path() + data, httpRequest.getBody());
    }
}
