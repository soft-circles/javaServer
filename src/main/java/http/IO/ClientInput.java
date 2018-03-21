package http.IO;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientInput {
    private Scanner scanner;

    public ClientInput(Socket clientSocket) throws IOException {
        this.scanner = new Scanner(clientSocket.getInputStream(), "UTF8");
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
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < length; i++) {
            data.append(scanner.next());
        }
        return data.toString().getBytes();
    }
}
