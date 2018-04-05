package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;

public class LogsController implements IController {

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setBody("PUT /these HTTP/1.1 GET /log HTTP/1.1 HEAD /requests HTTP/1.1");
        httpResponse.addHeader("Content-Length", String.valueOf(httpResponse.getBody().length));
        return httpResponse;
    }
}
