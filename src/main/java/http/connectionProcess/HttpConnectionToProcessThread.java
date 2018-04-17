package http.connectionProcess;

public class HttpConnectionToProcessThread implements Runnable {

    private final HttpConnectionToProcess httpConnectionToProcess;

    public HttpConnectionToProcessThread(HttpConnectionToProcess httpConnectionToProcess) {
        this.httpConnectionToProcess = httpConnectionToProcess;
    }

    @Override
    public void run() {
        httpConnectionToProcess.execute();
    }
}
