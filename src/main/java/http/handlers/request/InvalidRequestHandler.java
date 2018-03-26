package http.handlers.request;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.StatusMessages;
import http.utils.RouteChecker;

public class InvalidRequestHandler implements IRequestHandler {
    public InvalidRequestHandler() { }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        if (RouteChecker.validRoute(httpRequest.path()) && !RouteChecker.validAction(httpRequest.path(), httpRequest.method())) {
           httpResponse.setStatus("405");
           httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(405).toString());
        } else {
            httpResponse.setStatus("404");
            httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(404).toString());
            httpResponse.setBody("Content not found".getBytes());
        }
        httpResponse.addHeader("Content-Type", "text/html");
        return httpResponse;
    }
}
