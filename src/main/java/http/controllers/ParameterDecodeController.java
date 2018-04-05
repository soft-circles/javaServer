package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;

import java.util.Map;

public class ParameterDecodeController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        for(Map.Entry<String, String> e: httpRequest.getParameters().entrySet()) {
            httpResponse.addToBody(e.getKey() + " = " + e.getValue());
        }
        return httpResponse;
    }
}
