package http.utils.logger;

public class Logger implements ILogger {
    private StringBuilder log;

    public Logger() {
        this.log = new StringBuilder();
    }

    @Override
    public void addRequest(String info) {
        log.append(info);
    }

    @Override
    public String getLog() {
        return log.toString();
    }
}
