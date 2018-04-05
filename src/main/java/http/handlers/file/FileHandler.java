package http.handlers.file;

import http.IO.file.IFileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    private IFileIO IFileIO;

    public FileHandler(IFileIO IFileIO) {
        this.IFileIO = IFileIO;
    }


}
