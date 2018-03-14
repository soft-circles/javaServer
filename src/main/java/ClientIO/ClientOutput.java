package ClientIO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOutput {
    private Socket client;
    private PrintWriter writer;
    public ClientOutput(Socket client) throws IOException {
        this.client = client;
        this.writer = new PrintWriter(client.getOutputStream());
    }

    public void writeTo(String string) throws IOException {
        writer.println(string);
        writer.close();
    }
}
