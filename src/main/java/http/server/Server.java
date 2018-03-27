package http.server;
import http.IO.ClientInput;
import http.IO.ClientOutput;
import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.handlers.request.IRequestHandler;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.response.HttpResponseWriter;
import http.router.Router;
import http.socket.IClient;
import http.socket.Client;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public Server(int portNum, String directory) throws IOException, InvalidRequestException, InvalidPathException {
        ServerSocket socket = new ServerSocket(portNum);
        while (true) {
        System.out.println("Waiting for client...");
        IClient client = new Client(socket);
        System.out.println("Client connected");
        ClientInput clientInput = new ClientInput(client.getInputStream());
        String rawRequest = clientInput.getRawRequestString();
        HttpRequest httpRequest = new HttpRequest(rawRequest);
        if (httpRequest.getContentLength() > 0) {
            httpRequest.setBody(clientInput.getBytes(httpRequest.getContentLength()));
        }
        System.out.println(rawRequest);
        IRequestHandler handler = Router.getHandler(httpRequest, new FileIO(directory));
        HttpResponse httpResponse = handler.generateResponse(httpRequest);
        ClientOutput clientOutput = new ClientOutput(client.getOutputStream());
        new HttpResponseWriter().sendHttpResponse(client, httpResponse);
        client.closeConnection();
        System.out.println(httpResponse.fullResponse());
        }
    }
}




