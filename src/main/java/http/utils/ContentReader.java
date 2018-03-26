package http.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentReader {

    public static String getFileType(String path) {
        Pattern p = Pattern.compile("(\\.[^.]+)$");
        Matcher m = p.matcher(path);
        if (m.find() && VALID_FILE_TYPES.containsKey(m.group(1))) {
            return VALID_FILE_TYPES.get(m.group(1));
        } else {
            return "text/html";
        }
    }

    public static final Map<String, String> VALID_FILE_TYPES = types();

    private static Map<String, String> types() {
        Map<String, String> types = new HashMap<>();
        types.put(".jpeg", "image/jpeg");
        types.put(".png", "image/png");
        types.put(".gif", "image/gif");
        types.put(".txt", "text/plain");
        return types;
    }
}
