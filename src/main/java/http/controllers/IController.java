package http.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public interface IController {

    HttpResponse generateResponse(HttpRequest httpRequest) throws IOException;
}
