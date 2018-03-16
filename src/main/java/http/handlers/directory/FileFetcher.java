package http.handlers.directory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFetcher {

    public static List<String> fetch(String path) {
       //TODO Find solution to not hardcode next three lines
        if (path.matches("^/$")) {
            path = "../cob_spec/public";
        }
        List<String> results = new ArrayList<>();
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getName());
                }
            }
        }
        return results;
    }
}
