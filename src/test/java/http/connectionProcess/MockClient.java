package http.connectionProcess;

import http.client.IClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MockClient implements IClient {
    private long closedTime;
    private int sleepTime;

    public MockClient(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void sleep() {
        try {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return IOUtils.toInputStream("test", "UTF-8");
    }

    @Override
    public void write(byte[] bytes) throws IOException {
    }


    @Override
    public void closeConnection() throws IOException {
        sleep();
        closedTime = System.currentTimeMillis();
    }

    @Override
    public void openConnection() throws IOException {

    }

    public long getClosedTime() {
        return closedTime;
    }
}
