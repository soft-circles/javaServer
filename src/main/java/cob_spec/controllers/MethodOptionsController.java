package cob_spec.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Router;

public class MethodOptionsController implements IController {

    private final Router router;

    public MethodOptionsController(Router router) {
        this.router = router;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        switch (httpRequest.method()) {
            case OPTIONS:
                return handleOptions(httpRequest);
            default:
                return handleDefault();
        }
    }

    private HttpResponse handleDefault() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setEmptyBody();
        return httpResponse;
    }

    private HttpResponse handleOptions(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.addHeader("Allow", router.getRoute(httpRequest.path()).getHttpMethodsAsString());
        httpResponse.setEmptyBody();
        return httpResponse;
    }
}
