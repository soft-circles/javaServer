package http.IO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOutput {
    private PrintWriter writer;
    public ClientOutput(Socket client) throws IOException {
        this.writer = new PrintWriter(client.getOutputStream());
    }

    public void writeTo(String string){
        writer.println(string);
        writer.close();
    }
}
