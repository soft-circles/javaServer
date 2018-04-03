package http.controllers;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class PatchController implements IController {
    private final FileIO fileIO;

    public PatchController(FileIO fileIO) {
        this.fileIO = fileIO;
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
        if((DigestUtils.shaHex(fileIO.readFile(httpRequest.path())).equals(httpRequest.headers().get("If-Match")))) {
            fileIO.createFile(httpRequest.path(),httpRequest.getBody());
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setStatus("204");
            httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
            httpResponse.addHeader("Content-Location", httpRequest.path());
            httpResponse.addHeader("ETag", DigestUtils.shaHex(fileIO.readFile(httpRequest.path())));
            httpResponse.setBody("");
            return httpResponse;
        } else {
            return new InvalidRequestController().generateResponse(httpRequest);
        }
    }
    private HttpResponse handleGet(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setBody(fileIO.readFile(httpRequest.path()));
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        return httpResponse;
    }
}
