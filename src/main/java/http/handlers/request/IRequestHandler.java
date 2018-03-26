package http.handlers.request;

import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public interface IRequestHandler {

    HttpResponse returnResponse(HttpRequest httpRequest) throws IOException, InvalidPathException;
}
