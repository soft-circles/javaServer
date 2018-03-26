package http.handlers.request;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;

public class InvalidRequestHandler implements IRequestHandler {
    public InvalidRequestHandler() { }

    @Override
    public HttpResponse returnResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("404");
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(404).toString());
        httpResponse.setBody("Content not found".getBytes());
        return httpResponse;
    }
}
