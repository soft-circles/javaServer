package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.directory.DirectoryHandler;
import http.handlers.redirect.RedirectHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Redirects;
import http.status.StatusMessages;
import http.utils.RouteChecker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class GetRequestHandler extends HeadRequestHandler implements IRequestHandler {

    private final FileIO fileIO;

    public GetRequestHandler(FileIO fileIO) {
        super(fileIO);
        this.fileIO = fileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        DirectoryHandler directoryHandler = new DirectoryHandler(httpRequest, fileIO);
        RedirectHandler redirectHandler = new RedirectHandler();
        if (RouteChecker.validRoute(httpRequest.path()) && fileIO.exists(httpRequest.path())) {
            return createResponse(directoryHandler, httpRequest);
        } else if (Redirects.isRedirect(httpRequest.path())) {
            return redirectHandler.generateResponse(httpRequest);
        } else {
            return invalidRequestHandler.generateResponse(httpRequest);
        }
    }

    private HttpResponse createResponse(DirectoryHandler directoryHandler, HttpRequest httpRequest) throws IOException {
        HttpResponse httpResponse = directoryHandler.generateResponse();
        if (httpRequest.path().equals("/coffee")){
            httpResponse.setStatus("418");
            httpResponse.setReasonPhrase((StatusMessages.STATUSES.get(418).toString()));
            httpResponse.setBody("I'm a teapot\n");
        } else {
            httpResponse.setStatus("200");
            httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        }
        return httpResponse;
    }
}
