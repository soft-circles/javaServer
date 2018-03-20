package http.handlers.directory;

import http.response.HttpResponse;

import java.io.IOException;

public interface IResourceHandler {

    HttpResponse generateResponse() throws IOException;
}
