import Server.Server;

import java.io.IOException;

public class Main {

    static public void main(String args[]) throws IOException {
        Server server = new Server(Integer.parseInt(args[0]));
    }
}
