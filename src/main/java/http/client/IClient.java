package http.client;

import java.io.IOException;
import java.io.InputStream;

public interface IClient {
    InputStream getInputStream() throws IOException;
    void write(byte[] bytes) throws IOException;
    void closeConnection() throws IOException;
    void openConnection() throws IOException;
}
