package http.logger;

import http.request.HttpRequest;

public interface ILogger {
    void addRequest(HttpRequest httpRequest);
    String getLog();
}
