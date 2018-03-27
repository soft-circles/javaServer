package http.utils;

import java.util.HashMap;
import java.util.Map;

public class ServerConfig {

    public static final Map<String, String> OPTIONS = options();

    private static Map<String,String> options() {
        Map<String, String> options = new HashMap<>();
        options.put("-d", "./cob_spec/public");
        options.put("-p", System.getenv("PORT"));
        return options;
    }
}
