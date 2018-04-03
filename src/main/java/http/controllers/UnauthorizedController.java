package http.controllers;

import http.IO.file.InvalidPathException;
import http.handlers.auth.IAuth;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

import java.io.IOException;
import java.util.Base64;

public class UnauthorizedController implements IController {

    private final IAuth authHandler;

    public UnauthorizedController(IAuth authHandler) {
        this.authHandler = authHandler;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("401");
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        String encoding = Base64.getEncoder().encodeToString(authHandler.authString().getBytes());
        httpResponse.addHeader("WWW-Authenticate", "Basic " + encoding);
        httpResponse.setBody("");
        return httpResponse;
    }
}
