package Port;

import java.io.IOException;
import java.net.Socket;

public class Port {
    private Socket socket;

    public Port(String hostname, int portNumber) throws IOException {
       this.socket = new Socket(hostname, portNumber);
    }

    public Socket getSocket() {
        return socket;
    }
}
