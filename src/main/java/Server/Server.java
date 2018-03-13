package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public ServerSocket socket;
    private Socket sock;

    public Server(int portNum) throws IOException {
        String message = "HTTP/2.0 200 OK";
        this.socket = new ServerSocket(portNum);
        while(true) {
            System.out.println("Waiting for client...");
            Socket sock = socket.accept();
            System.out.println("Client connected");
            InputStreamReader stream = new InputStreamReader(sock.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String newMessage = reader.readLine();
            System.out.println(newMessage);
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            writer.println(message);
            writer.close();
            System.out.println(message);
        }
    }

    public InputStreamReader getInputStream() throws IOException {
        return new InputStreamReader(sock.getInputStream());
    }
}
