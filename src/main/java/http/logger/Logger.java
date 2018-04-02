package http.logger;

import http.request.HttpRequest;

public class Logger implements ILogger {
    private StringBuilder log;

    public Logger() {
        this.log = new StringBuilder();
    }

    @Override
    public void addRequest(HttpRequest httpRequest) {
        log.append(httpRequest.requestLine());
        log.append("\n");
    }

    @Override
    public String getLog() {
        return log.toString();
    }
}
