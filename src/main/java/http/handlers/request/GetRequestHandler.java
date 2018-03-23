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

    public GetRequestHandler(FileIO fileIO) {
        super(fileIO);
        this.fileIO = fileIO;
    }

    @Override
    public HttpResponse returnResponse(HttpRequest httpRequest) throws IOException {
        DirectoryHandler directoryHandler = new DirectoryHandler(httpRequest, fileIO);
        if (PathChecker.validRoute(httpRequest.path())) {
            return createResponse(directoryHandler);
        } else {
            return invalidResourceHandler.generateResponse();
        }
    }

    private HttpResponse createResponse(DirectoryHandler directoryHandler) throws IOException {
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
