package http.controllers;

import http.IO.file.IFileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class PatchController implements IController {
    private final IFileIO IFileIO;

    public PatchController(IFileIO IFileIO) {
        this.IFileIO = IFileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException, InvalidPathException {
        switch (httpRequest.method()) {
            case GET:
                return handleGet(httpRequest);
            case PATCH:
                return handlePatch(httpRequest);
            default:
                return new InvalidRequestController().generateResponse(httpRequest);
        }
    }

    private HttpResponse handlePatch(HttpRequest httpRequest) throws InvalidStatusCodeException, IOException, InvalidPathException {
        if((DigestUtils.shaHex(IFileIO.readFile(httpRequest.path())).equals(httpRequest.headers().get("If-Match")))) {
            IFileIO.createFile(httpRequest.path(),httpRequest.getBody());
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setStatus("204");
            httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
            httpResponse.addHeader("Content-Location", httpRequest.path());
            httpResponse.addHeader("ETag", DigestUtils.shaHex(IFileIO.readFile(httpRequest.path())));
            httpResponse.setBody("");
            return httpResponse;
        } else {
            return new InvalidRequestController().generateResponse(httpRequest);
        }
    }
    private HttpResponse handleGet(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setBody(IFileIO.readFile(httpRequest.path()));
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        return httpResponse;
    }
}
