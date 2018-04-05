package http.server;

import http.router.NoAuthOnRouteException;
import http.utils.MainArgumentParser;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Map<String, String> parsedArguments = parseArguments(args);

        int portNumber = Integer.parseInt(parsedArguments.get("PortNumber"));
        String directory = parsedArguments.get("Directory");

        if (System.getenv("PORT") != null) {
            portNumber = Integer.parseInt(System.getenv("PORT"));
        }
        new Server(portNumber, directory);
    }

    private static Map<String, String> parseArguments(String[] args) throws Exception {
        MainArgumentParser argumentParser = new MainArgumentParser()
                .addFlag("-p", "PortNumber", "5000")
                .addFlag("-d", "Directory", "./cob_spec/public");

        return argumentParser.parse(args);
    }
}
