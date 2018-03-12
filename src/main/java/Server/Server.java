package Server;

import Port.Port;

import java.io.IOException;

public class Server {
    public Port port;

    public Server(int portNum) throws IOException {
        this.port = new Port("127.0.0.1", portNum);
    }
}
