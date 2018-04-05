package http.controllers;

import http.IO.file.IFileIO;
import http.IO.file.InvalidPathException;
import http.handlers.file.FileHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.IRouter;
import http.router.Router;
import http.status.InvalidStatusCodeException;
import http.status.StatusMessages;

import java.io.IOException;

public class DeleteFileController implements IController {

    private final IRouter router;
    private final IFileIO fileIO;

    public DeleteFileController(IRouter router, IFileIO fileIO) {
        this.fileIO = fileIO;
        this.router = router;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException, InvalidPathException {
        removeRoute(httpRequest.path());
        deleted(httpRequest.path());
        return createResponse();
    }

    public boolean deleted(String path) throws IOException, InvalidPathException {
        return fileIO.deleteFile(path);
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
