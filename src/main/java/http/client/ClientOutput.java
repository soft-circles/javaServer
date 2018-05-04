package http.client;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ClientOutput implements IClientOutput {
    private PrintWriter writer;
    public ClientOutput(OutputStream outputStream) {
        this.writer = new PrintWriter(outputStream);
    }

    @Override
    public void writeTo(String string){
        writer.println(string);
        writer.close();
    }
}
