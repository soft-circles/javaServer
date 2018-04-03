package http.utils.logger;

public interface ILogger {
    void addRequest(String info);
    String getLog();
}
