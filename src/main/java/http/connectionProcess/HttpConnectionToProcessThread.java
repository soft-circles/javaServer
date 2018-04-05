package http.connectionProcess;

import http.IO.file.InvalidPathException;
import http.request.error.InvalidRequestException;
import http.router.NoAuthOnRouteException;
import http.status.InvalidStatusCodeException;

import java.io.IOException;

public class HttpConnectionToProcessThread implements Runnable {

    private final HttpConnectionToProcess httpConnectionToProcess;

    public HttpConnectionToProcessThread(HttpConnectionToProcess httpConnectionToProcess) {
        this.httpConnectionToProcess = httpConnectionToProcess;
    }

    @Override
    public void run() {
        try {
            httpConnectionToProcess.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoAuthOnRouteException e) {
            e.printStackTrace();
        } catch (InvalidStatusCodeException e) {
            e.printStackTrace();
        } catch (InvalidPathException e) {
            e.printStackTrace();
        }
    }
}
