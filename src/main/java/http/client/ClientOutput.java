package http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ClientOutput implements IClientOutput {
    public final OutputStream output;
    private PrintWriter writer;
    public ClientOutput(OutputStream outputStream) throws IOException {
        this.output = outputStream;
        this.writer = new PrintWriter(outputStream);
    }

    @Override
    public void writeTo(String string){
        writer.println(string);
        writer.close();
    }

    public void writeBytes(byte[] bytes) throws IOException {
        output.write(bytes);
    }
}
