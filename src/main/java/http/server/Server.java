package http.server;
import http.IO.ClientInput;
import http.IO.ClientOutput;
import http.handlers.response.ResponseHandler;
import http.response.HttpResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(int portNum) throws IOException {
        ServerSocket socket = new ServerSocket(portNum);
        while (true) {
        System.out.println("Waiting for client...");
        Socket connectedSocket = socket.accept();
        ClientInput clientInput = new ClientInput(connectedSocket);
        System.out.println("Client connected");
        String rawRequest = clientInput.getRawRequestString();
        HttpResponse httpResponse = ResponseHandler.buildResponse(rawRequest);
        System.out.println(rawRequest);
        ClientOutput clientOutput = new ClientOutput(connectedSocket);
        clientOutput.writeTo(httpResponse.fullResponse());
        System.out.println(httpResponse.fullResponse());
        }
    }
}
