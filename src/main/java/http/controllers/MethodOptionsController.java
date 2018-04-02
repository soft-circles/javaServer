package http.controllers;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Router;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

import java.io.IOException;

public class MethodOptionsController implements IController {

    private final Router router;

    public MethodOptionsController(Router router) {
        this.router = router;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        switch (httpRequest.method()) {
            case OPTIONS:
                return handleOptions(httpRequest);
            default:
                return handleDefault();
        }
    }

    private HttpResponse handleDefault() throws InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        httpResponse.setBody("");
        return httpResponse;
    }

    private HttpResponse handleOptions(HttpRequest httpRequest) throws InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.addHeader("Allow", router.getRoute(httpRequest.path()).getHttpMethodsAsString());
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        httpResponse.setBody("");
        return httpResponse;
    }
}
