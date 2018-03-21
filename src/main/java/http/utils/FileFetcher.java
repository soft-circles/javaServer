package http.utils;

import http.IO.file.FileIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFetcher {

    public static List<String> fetch(String path) {
        FileIO fileIO = new FileIO();
        List<String> results = new ArrayList<>();
        File[] files = new File(fileIO.getWorkingDirectory() + path).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getName());
                }
            }
        }
        return results;
    }

    public static String parseTextFile(String path) throws IOException {
        FileIO fileIO = new FileIO();
        Pattern p = Pattern.compile("([^/]*)$");
        Matcher matcher = p.matcher(path);
        if (matcher.find()) {
            Path file = Paths.get(fileIO.getWorkingDirectory() + "/" + matcher.group(0));
            if (Files.exists(file)) {
                return new String(Files.readAllBytes(file));
            }
        }
        return "";
    }
}
