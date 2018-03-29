package http.utils;
import java.util.HashMap;
import java.util.Map;

public class MainArgumentParser {

    private Map<String, String> flags;
    private Map<String, String> defaults;

    public MainArgumentParser() {
        flags = new HashMap<>();
        defaults = new HashMap<>();
    }

    public MainArgumentParser addFlag(String flag, String name) {
        flags.put(flag, name);
        return this;
    }

    public Map<String,String> parse(String[] args) throws Exception {
        Map<String, String> parsedArguments = defaults;
        for (int i = 0; i < args.length; i++) {
            if (flags.containsKey(args[i])) {
                parsedArguments.put(flags.get(args[i]), args[++i]);
            }
            else {
                throw new Exception();
            }
        }
        return parsedArguments;
    }

    public MainArgumentParser addFlag(String flag, String name, String defaultValue) {
        flags.put(flag, name);
        defaults.put(name, defaultValue);
        return this;
    }
}
