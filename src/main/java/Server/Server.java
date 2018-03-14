package Server;
import ClientIO.ClientInput;
import ClientIO.ClientOutput;
import Handlers.ResponseHandler;
import HttpResponse.HttpResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public ServerSocket socket;
    private HttpResponse httpResponse;

    public Server(int portNum) throws IOException {
        String message = "HTTP/2.0 200 OK";
        this.socket = new ServerSocket(portNum);
        while (true) {
        System.out.println("Waiting for client...");
        Socket connectedSocket = socket.accept();
        ClientInput clientInput = new ClientInput(connectedSocket);
        System.out.println("Client connected");
        String rawRequest = clientInput.getRawRequestString();
        httpResponse = ResponseHandler.buildResponse(rawRequest);
        System.out.println(rawRequest);
        ClientOutput clientOutput = new ClientOutput(connectedSocket);
        clientOutput.writeTo(httpResponse.full_response());
        System.out.println(httpResponse.full_response());
        }
    }
}
