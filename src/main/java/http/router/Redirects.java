package http.router;

import java.util.*;

public class Redirects {
    public static final Map<String, ArrayList<String>> VALID_REDIRECTS = paths();

    private static Map<String, ArrayList<String>> paths() {
        Map<String, ArrayList<String>> paths = new HashMap<>();
        paths.put("/", new ArrayList<>(Arrays.asList("/redirect")));
        return paths;
    }

    public static boolean isRedirect(String path) {
        Iterator<ArrayList<String>> iterator = paths().values().iterator();
        while (iterator.hasNext()) {
            ArrayList<String> redirectList = iterator.next();
            if (redirectList.contains(path)) {
                return true;
            }
        }
        return false;
    }
}
