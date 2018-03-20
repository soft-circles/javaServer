package http.handlers.request;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.PathChecker;

import java.io.IOException;


public class GetRequestHandler extends HeadRequestHandler implements IRequestHandler {
    public GetRequestHandler(HttpRequest httpRequest) {
        super(httpRequest);
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
