package http.utils;

import java.util.HashMap;
import java.util.Map;

public class ServerConfig {

    public static final Map<String, String> OPTIONS = options();

    private static Map<String,String> options() {
        Map<String, String> options = new HashMap<>();
        options.put("-d", "./public");
        options.put("-p", "5000");
        return options;
    }
}
