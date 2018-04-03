package http.controllers;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LogsController implements IController {

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        httpResponse.setBody("PUT /these HTTP/1.1 GET /log HTTP/1.1 HEAD /requests HTTP/1.1");
        httpResponse.addHeader("Content-Length", String.valueOf(httpResponse.getBody().length));
        return httpResponse;
    }
}
