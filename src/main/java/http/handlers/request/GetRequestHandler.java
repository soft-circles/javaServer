package http.handlers.request;

import http.IO.file.FileIO;
import http.handlers.directory.DirectoryHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

import java.io.IOException;


public class GetRequestHandler extends HeadRequestHandler implements IRequestHandler {
    private final FileIO fileIO;
    private final DirectoryHandler directoryHandler;

    public GetRequestHandler(HttpRequest httpRequest, FileIO fileIO) {
        super(httpRequest);
        this.fileIO = fileIO;
        this.directoryHandler = new DirectoryHandler(httpRequest, fileIO);
    }

    @Override
    public HttpResponse returnResponse() throws IOException {
        if (PathChecker.validRoute(httpRequest.path())) {
            return createResponse();
        } else {
            return invalidResourceHandler.generateResponse();
        }
    }

    private HttpResponse createResponse() throws IOException {
        HttpResponse httpResponse = directoryHandler.generateResponse();
        if ("I'm a teapot\n".equals(httpResponse.getBody())) {
            httpResponse.setStatus("418");
            httpResponse.setReasonPhrase((StatusMessages.STATUSES.get(418).toString()));
        } else  {
            httpResponse.setStatus("200");
            httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        }
        return httpResponse;
    }
}
