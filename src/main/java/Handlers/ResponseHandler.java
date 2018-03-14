package Handlers;

import HttpRequest.HttpRequest;
import HttpResponse.HttpResponse;
import HttpResponse.HttpResponseBuilder;

public class ResponseHandler {
    private static HttpRequest httpRequest;
    private ResponseHandler() {}

    public static HttpResponse buildResponse(String rawRequest) {
        httpRequest = new HttpRequest(rawRequest);
        HttpResponse response = HttpResponseBuilder.build(httpRequest);
        return response;
    }
}
