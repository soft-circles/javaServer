package http.controllers;

import http.IO.file.IFileIO;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.IRouter;
import http.status.Status;

import java.io.IOException;
import java.util.Arrays;

public class CatFormController implements IController {
    private final IRouter router;
    private final IFileIO IFileIO;

    public CatFormController(IRouter router, IFileIO IFileIO) {
        this.router = router;
        this.IFileIO = IFileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        switch(httpRequest.method()) {
            case GET:
                return handleGet(httpRequest);
            case POST:
                return handlePost(httpRequest);
            case PUT:
                return handlePut(httpRequest);
            case DELETE:
                return handleDelete(httpRequest);
            default:
                return new InvalidRequestController().generateResponse(httpRequest);
        }
    }

    private HttpResponse handleGet(HttpRequest httpRequest) throws IOException  {
        return new DirectoryController(IFileIO).generateResponse(httpRequest);
    }

    private HttpResponse handlePost(HttpRequest httpRequest) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        createFileAtDifferentLocation(httpRequest);
        String fileLocation = httpRequest.path() + "/data";
        addRoute(fileLocation);
        httpResponse.addHeader("Location", fileLocation);
        httpResponse.setStatus(Status.Created);
        httpResponse.setBody("");
        return httpResponse;
    }

    private void addRoute(String path) {
        router.addRoute(path, Arrays.asList(httpMethod.GET, httpMethod.PUT, httpMethod.DELETE), this);
    }

    private void createFileAtPathLocation(HttpRequest httpRequest) throws IOException {
        IFileIO.createFile(httpRequest.path(), httpRequest.getBody());
    }

    private void createFileAtDifferentLocation(HttpRequest httpRequest) throws IOException {
        String data = "/data";
        IFileIO.createFile(httpRequest.path() + data, httpRequest.getBody());
    }

    private HttpResponse handlePut(HttpRequest httpRequest) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        createFileAtPathLocation(httpRequest);
        httpResponse.setStatus(Status.OK);
        httpResponse.setBody("");
        return httpResponse;
    }

    private HttpResponse handleDelete(HttpRequest httpRequest) throws IOException {
        return new DeleteFileController(router, IFileIO).generateResponse(httpRequest);
    }
}
