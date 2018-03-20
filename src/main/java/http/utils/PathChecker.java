package http.utils;

import http.IO.file.FileIO;
import http.method.httpMethod;

import static http.router.Routes.VALID_PATHS;

public class PathChecker {

    public static boolean validRoute(String path) {
        return VALID_PATHS.containsKey(path) && validPath(path);
    }

    private static boolean validPath(String path) {
        return new FileIO().exists(path);
    }

    public static boolean writePermitted(String path) {
        return VALID_PATHS.get(path).contains(httpMethod.POST);
    }
}
