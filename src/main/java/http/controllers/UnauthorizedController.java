package http.controllers;

import http.handlers.auth.IAuth;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;

import java.util.Base64;

public class UnauthorizedController implements IController {

    private final IAuth authHandler;

    public UnauthorizedController(IAuth authHandler) {
        this.authHandler = authHandler;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.Unauthorized);
        String encoding = Base64.getEncoder().encodeToString(authHandler.authString().getBytes());
        httpResponse.addHeader("WWW-Authenticate", "Basic " + encoding);
        httpResponse.setBody("");
        return httpResponse;
    }
}
