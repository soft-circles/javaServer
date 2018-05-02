package cob_spec.controllers;

import http.IO.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.IRouter;
import http.status.Status;

import java.io.IOException;

public class DeleteFileController implements IController {

    private final IRouter router;
    private final IFileIO fileIO;

    public DeleteFileController(IRouter router, IFileIO fileIO) {
        this.fileIO = fileIO;
        this.router = router;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        removeRoute(httpRequest.path());
        fileIO.deleteFile(httpRequest.path());
        return createResponse();
    }

    private void removeRoute(String path) {
        router.removeRoute(path);
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.OK);
        httpResponse.setEmptyBody();
        return httpResponse;
    }
}
