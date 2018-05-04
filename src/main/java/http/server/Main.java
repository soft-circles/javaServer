package http.server;

import cob_spec.router.CobSpecRouter;
import http.IO.FileIO;
import http.router.Router;
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
        Router router = new CobSpecRouter().getRouter(new FileIO(directory));
        Server server = new Server(portNumber, directory, router);
        server.run();
    }

    private static Map<String, String> parseArguments(String[] args) throws Exception {
        MainArgumentParser argumentParser = new MainArgumentParser()
                .addFlag("--port", "PortNumber", "5000")
                .addFlag("-p", "PortNumber", "5000")
                .addFlag("--directory", "Directory", "./cob_spec/public")
                .addFlag("-d", "Directory", "./cob_spec/public");

        return argumentParser.parse(args);
    }


}
