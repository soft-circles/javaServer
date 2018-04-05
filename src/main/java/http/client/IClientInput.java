package http.client;

import java.io.IOException;

public interface IClientInput {
    String getRawRequestString() throws IOException;
    byte[] getBytes(int length) throws IOException;
}
