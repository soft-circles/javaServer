package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.directory.FileFetcher;
import http.handlers.directory.HTMLgenerator;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

import static http.router.Routes.VALID_PATHS;

public class DirectoryHandler implements IRequestHandler {
    private HttpRequest httpRequest;
    public DirectoryHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public HttpResponse returnResponse() {
        if (validRoute()) {
            return createResponse();
        } else {
            return new InvalidRequestHandler(httpRequest).returnResponse();
        }
    }

    private boolean validRoute() {
        return VALID_PATHS.containsKey(httpRequest.path()) && validPath();
    }

    private boolean validPath() {
        return new FileIO("").exists(httpRequest.path());
    }

    private String getDirectoryContents() {
        return HTMLgenerator.generate(FileFetcher.fetch(httpRequest.path()));
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.setBody(getDirectoryContents());
        return httpResponse;
    }

}
