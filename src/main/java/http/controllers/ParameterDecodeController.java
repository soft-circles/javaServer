package http.controllers;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

import java.io.IOException;
import java.util.Map;

public class ParameterDecodeController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        for(Map.Entry<String, String> e: httpRequest.getParameters().entrySet()) {
            httpResponse.addToBody(e.getKey() + " = " + e.getValue());
        }
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        return httpResponse;
    }
}
