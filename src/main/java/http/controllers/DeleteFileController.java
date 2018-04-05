package http.controllers;

import http.IO.file.IFileIO;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Router;
import http.status.InvalidStatusCodeException;
import http.status.StatusMessages;

import java.io.IOException;

public class DeleteFileController implements IController {

    private final FileHandler fileHandler;
    private final InvalidRequestController invalidRequestHandler;
    private final Router router;

    public DeleteFileController(Router router, IFileIO IFileIO) {
        this.invalidRequestHandler = new InvalidRequestController();
        this.fileHandler = new FileHandler(IFileIO);
        this.router = router;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException {
        removeRoute(httpRequest.path());
        deleted(httpRequest.path());
        return createResponse();
    }

    public boolean deleted(String path) throws IOException {
        return fileHandler.deleteFile(path);
    }

    private void removeRoute(String path) {
        router.removeRoute(path);
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.addToBody("");
        return httpResponse;
    }
}
