package http.server;


import http.IO.file.InvalidPathException;
import http.request.error.InvalidRequestException;
import http.router.NoAuthOnRouteException;
import http.status.InvalidStatusCodeException;
import http.utils.MainArgumentParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception, InvalidPathException, InvalidStatusCodeException, NoAuthOnRouteException {
        Map<String, String> parsedArguments = parseArguments(args);

        int portNumber = Integer.parseInt(parsedArguments.get("PortNumber"));
        String directory = parsedArguments.get("Directory");

        new Server(portNumber, directory);
    }

    private static Map<String, String> parseArguments(String[] args) throws Exception {
        MainArgumentParser argumentParser = new MainArgumentParser()
                .addFlag("-p", "PortNumber", "5000")
                .addFlag("-d", "Directory", "./cob_spec/public");

        return argumentParser.parse(args);
    }
}
