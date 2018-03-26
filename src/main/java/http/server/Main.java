package http.server;


import http.IO.file.InvalidPathException;
import http.request.error.InvalidRequestException;
import http.utils.MainArgumentParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidRequestException, InvalidPathException {
        MainArgumentParser argumentParser = new MainArgumentParser(args);
        System.out.println("STARTING SERVER");
        new Server(argumentParser.getPortNumber(), argumentParser.getWorkingDirectory());
    }
}
