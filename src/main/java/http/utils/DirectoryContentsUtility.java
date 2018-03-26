package http.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryContentsUtility {

    public static List<String> listDirectoryContents(String[] fileNames) {
        List<String> results = new ArrayList<>();
        for (String fileName : fileNames) {
            results.add(fileName);
        }
        return results;
    }
}
