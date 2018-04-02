package http.controllers;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

import java.io.IOException;
import java.util.Base64;

public class UnauthorizedController implements IController {

    private final String authString;

    public UnauthorizedController(String authString) {
        this.authString = authString;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("401");
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        String encoding = Base64.getEncoder().encodeToString(authString.getBytes());
        httpResponse.addHeader("WWW-Authenticate", "Basic " + encoding);
        httpResponse.setBody("");
        return httpResponse;
    }
}
