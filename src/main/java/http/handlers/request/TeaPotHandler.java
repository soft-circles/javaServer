package http.handlers.request;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;


public class TeaPotHandler implements IRequestHandler {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException {
        switch (httpRequest.method()) {
            case GET:
                return handleGet(httpRequest);
            default:
                return new InvalidRequestHandler().generateResponse(httpRequest);
        }
    }

    private HttpResponse handleGet(HttpRequest httpRequest) {
        HttpResponse response = new HttpResponse();
        if (httpRequest.path().equals("/coffee")) {
            response.setStatus("418");
        } else {
            response.setStatus("200");
            response.setBody("I'm a teapot");
        }
        return response;
    }
}
