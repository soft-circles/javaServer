package http.handlers.request;

import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.RouteChecker;

import java.io.IOException;


public class HeadRequestHandler implements IRequestHandler {
    protected InvalidRequestHandler invalidRequestHandler;

    public HeadRequestHandler(FileIO fileIo) {
        this.invalidRequestHandler = new InvalidRequestHandler();
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException {
        if (RouteChecker.validRoute(httpRequest.path())) {
            return createResponse();
        } else {
            return invalidRequestHandler.generateResponse(httpRequest);
        }
    }

    private HttpResponse createResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(200).toString());
        httpResponse.addToBody("");
        return httpResponse;
    }
}
