package http.client;

import java.io.InputStream;
import java.util.Scanner;

public class ClientInput implements IClientInput {
    private Scanner scanner;

    public ClientInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public String getRawRequestString() {
        StringBuilder request = new StringBuilder();
        String line = "";
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
        }
        while (!line.isEmpty()) {
            request.append(line);
            request.append("\n");
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else  {
                break;
            }
        }
        return request.toString();
    }

    @Override
    public byte[] getBytes(int length){
        scanner.useDelimiter("");
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < length; i++) {
            data.append(scanner.next());
        }
        return data.toString().getBytes();
    }
}
