package http.handlers.response;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.HttpResponseBuilder;

public class ResponseHandler {
    private static HttpRequest httpRequest;
    private ResponseHandler() {}

    public static HttpResponse buildResponse(String rawRequest) {
        httpRequest = new HttpRequest(rawRequest);
        HttpResponse response = HttpResponseBuilder.build(httpRequest);
        return response;
    }
}
