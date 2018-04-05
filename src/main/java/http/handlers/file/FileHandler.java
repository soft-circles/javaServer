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

    public void createFile(String filePath, byte[] data) throws IOException {
        if (data == null) {
            data = new byte[0];
        }
        Pattern p = Pattern.compile("([^/]*)$");
        Matcher matcher = p.matcher(filePath);
        if (matcher.find()) {
            Path file = Paths.get(IFileIO.getWorkingDirectory() + filePath);
            Files.write(file, data);
        }
    }

    public boolean deleteFile(String filePath) throws IOException {
        Pattern p = Pattern.compile("([^/]*)$");
        Matcher matcher = p.matcher(filePath);
        if (matcher.find()) {
            Path pathToFile = Paths.get(IFileIO.getWorkingDirectory() + filePath);
            Files.delete(pathToFile);
            return true;
        }
        return false;
    }
}
