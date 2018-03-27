package http.utils;

public class MainArgumentParser {
    public MainArgumentParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (ServerConfig.OPTIONS.containsKey(args[i])) {
                ServerConfig.OPTIONS.replace(args[i], args[++i]);
            }
        }
    }

    public int getPortNumber() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        } else  {
            return Integer.parseInt(ServerConfig.OPTIONS.get("-p"));
        }
    }

    public String getWorkingDirectory() {
        return ServerConfig.OPTIONS.get("-d");
    }
}