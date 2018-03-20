package http.IO;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientInput {
    private final Socket client;
    private Scanner scanner;

    public ClientInput(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.scanner = new Scanner(client.getInputStream(), "UTF8");
    }

    public String getRawRequestString() {
        StringBuilder request = new StringBuilder();
        String line = scanner.nextLine();
        while (!line.isEmpty()) {
            request.append(line);
            request.append("\n");
            line = scanner.nextLine();
        }
        return request.toString();
    }

    public byte[] getBytes(int length){
        scanner.useDelimiter("");
        String data = "";
        for (int i = 0; i < length; i++) {
            data += scanner.next();
        }
        return data.getBytes();
    }
}
