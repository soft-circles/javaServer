package http.handlers.request;

import http.response.HttpResponse;

import java.io.IOException;

public interface IRequestHandler {

    HttpResponse returnResponse() throws IOException;
}
