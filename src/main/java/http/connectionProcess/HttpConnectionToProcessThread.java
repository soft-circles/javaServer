package http.connectionProcess;

public class HttpConnectionToProcessThread implements Runnable {

    private final HttpConnectionToProcess httpConnectionToProcess;

    HttpConnectionToProcessThread(HttpConnectionToProcess httpConnectionToProcess) {
        this.httpConnectionToProcess = httpConnectionToProcess;
    }

    @Override
    public void run() {
        httpConnectionToProcess.execute();
    }
}
