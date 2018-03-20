package http.handlers.directory;

import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.utils.FileFetcher;
import http.utils.HTMLgenerator;

import java.io.IOException;

public class DirectoryHandler implements IResourceHandler {
    private final HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private FileIO fileIO;

    public DirectoryHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        httpResponse = new HttpResponse();
        fileIO = new FileIO();
    }

    @Override
    public HttpResponse generateResponse() throws IOException {
        return buildResponse();
    }

    private HttpResponse buildResponse() throws IOException {
        buildResponseBody();
        buildResponseHeaders();
        return httpResponse;
    }

    private void buildResponseHeaders() {
        httpResponse.addHeader("Content-Type", "text/html");
    }

    private void buildResponseBody() throws IOException {
        if (fileIO.isDirectory(httpRequest.path())) {
            httpResponse.setBody(generateDirectoryList());
        } else {
            httpResponse.setBody(FileFetcher.parseTextFile(httpRequest.path()));
        }
    }

    private String generateDirectoryList() {
        return HTMLgenerator.generate(FileFetcher.fetch(httpRequest.path()));
    }
}
