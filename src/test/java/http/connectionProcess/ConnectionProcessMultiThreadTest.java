package http.connectionProcess;

import http.response.HttpResponseWriter;
import http.router.Router;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConnectionProcessMultiThreadTest {

    private Router router;
    private HttpResponseWriter httpResponseWriter;
    private MockClient clientLongDelay;
    private MockClient clientShortDelay;

    @BeforeEach
    void setUp() {

        router = new Router();
        httpResponseWriter = new HttpResponseWriter();
    }

    @Test
    void LongDelayThreadFinishesAfterShortDelayThread() throws InterruptedException {
        clientLongDelay = new MockClient(1000);
        clientShortDelay = new MockClient(0);

        HttpConnectionToProcess httpConnectionToProcessLong = new HttpConnectionToProcess(clientLongDelay, router, httpResponseWriter);
        HttpConnectionToProcess httpConnectionToProcessShort = new HttpConnectionToProcess(clientShortDelay, router, httpResponseWriter);
        ConnectionProcessMultiThread connectionProcessMultiThread = new ConnectionProcessMultiThread();
        connectionProcessMultiThread.execute(httpConnectionToProcessLong);
        connectionProcessMultiThread.execute(httpConnectionToProcessShort);
        Thread.sleep(1500);
        assertTrue(clientShortDelay.getClosedTime() < clientLongDelay.getClosedTime());
    }
}