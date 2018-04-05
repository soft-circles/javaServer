package http.controllers;

import http.IO.file.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class PatchController implements IController {
    private final IFileIO IFileIO;

    public PatchController(IFileIO IFileIO) {
        this.IFileIO = IFileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        switch (httpRequest.method()) {
            case GET:
                return handleGet(httpRequest);
            case PATCH:
                return handlePatch(httpRequest);
            default:
                return new InvalidRequestController().generateResponse(httpRequest);
        }
    }

    private HttpResponse handlePatch(HttpRequest httpRequest) throws IOException {
        if((DigestUtils.shaHex(IFileIO.readFile(httpRequest.path())).equals(httpRequest.headers().get("If-Match")))) {
            IFileIO.createFile(httpRequest.path(),httpRequest.getBody());
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setStatus(Status.No_Content);
            httpResponse.addHeader("Content-Location", httpRequest.path());
            httpResponse.addHeader("ETag", DigestUtils.shaHex(IFileIO.readFile(httpRequest.path())));
            httpResponse.setBody("");
            return httpResponse;
        } else {
            return new InvalidRequestController().generateResponse(httpRequest);
        }
    }
    private HttpResponse handleGet(HttpRequest httpRequest) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.OK);
        httpResponse.setBody(IFileIO.readFile(httpRequest.path()));
        return httpResponse;
    }
}
