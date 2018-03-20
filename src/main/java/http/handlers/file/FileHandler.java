package http.handlers.file;

import http.IO.file.FileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    private FileIO fileIO;

    public FileHandler() {
        this.fileIO = new FileIO();
    }

    public void createFile(String filePath, byte[] data) throws IOException {
        Pattern p = Pattern.compile("([^/]*)$");
        Matcher matcher = p.matcher(filePath);
        if (matcher.find()) {
            Path file = Paths.get(FileIO.workingDirectory + "/" + matcher.group(0));
            Files.write(file, data);
        }
    }
}
