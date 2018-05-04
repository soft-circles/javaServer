package cob_spec.controllers;

import http.IO.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;

import java.io.IOException;

public class PostFileContentsController implements IController {
    private final IFileIO IFileIO;

    public PostFileContentsController(IFileIO IFileIO) {
        this.IFileIO = IFileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        switch (httpRequest.method()) {
            case GET:
                return new DirectoryController(IFileIO).generateResponse(httpRequest);
            default:
                HttpResponse httpResponse = new HttpResponse();
                if (IFileIO.isDirectory(httpRequest.path())) {
                    createFileAtDifferentLocation(httpRequest);
                    String fileLocation = httpRequest.path() + "/data";
                    httpResponse.addHeader("Location", fileLocation);
                    httpResponse.setStatus(Status.Created);
                } else {
                    createFileAtPathLocation(httpRequest);
                    httpResponse.setStatus(Status.OK);
                }
                httpResponse.setEmptyBody();
                return httpResponse;
        }
    }

    private void createFileAtPathLocation(HttpRequest httpRequest) throws IOException {
        IFileIO.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private void createFileAtDifferentLocation(HttpRequest httpRequest) throws IOException {
        String data = "/data";
        IFileIO.createFile(httpRequest.path() + data, httpRequest.getBody());
    }
}
