package http.server;

import http.IO.FileIO;
import http.IO.IFileIO;
import http.client.Client;
import http.client.IClient;
import http.connectionProcess.ConnectionProcessMultiThread;
import http.connectionProcess.HttpConnectionToProcess;
import http.controllers.*;
import http.handlers.auth.AuthHandler;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;
import http.response.HttpResponseWriter;
import http.router.Router;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Server {
    private final String directory;
    private final int portNumber;
    private Router router;

    public Server(int portNum, String directory, Router router) throws IOException {
        this.router = router;
        this.portNumber = portNum;
        this.directory = directory;
    }

    public void run() {
        System.out.println("Server starting on port: " + String.valueOf(portNumber) + " " + "with public directory: " + directory);
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConnectionProcessMultiThread connectionProcessMultiThread = new ConnectionProcessMultiThread();
        while (true) {
            IClient client = getiClient(socket);
            System.out.println("Client connected");
            HttpConnectionToProcess httpConnectionToProcess = new HttpConnectionToProcess(client, router, new HttpResponseWriter());
            connectionProcessMultiThread.execute(httpConnectionToProcess);
        }
    }
    private IClient getiClient(ServerSocket socket) {
        System.out.println("Waiting for client...");
        return new Client(socket);
    }
}




