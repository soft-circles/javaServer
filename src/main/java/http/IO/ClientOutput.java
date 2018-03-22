package http.IO;

import http.socket.IClient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOutput implements IClientOutput {
    private PrintWriter writer;
    public ClientOutput(OutputStream outputStream) throws IOException {
        this.writer = new PrintWriter(outputStream);
    }

    @Override
    public void writeTo(String string){
        writer.println(string);
        writer.close();
    }
}
