package http.IO.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO {
    private String workingDirectory;

    public FileIO(String path) {
        workingDirectory = path;
    }

    public boolean exists(String path) {
        File fileToCheck = new File(getPath(path));
        return fileToCheck.exists();
    }

    public boolean isFile(String path) {
        File fileToCheck = new File(getPath(path));
        return fileToCheck.isFile();
    }

    public boolean isDirectory(String path) {
        File directoryToCheck = new File(getPath(path));
        return directoryToCheck.isDirectory();
    }

    private String getPath(String fileName) {
        return workingDirectory + fileName;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public byte[] readFile(String fileName) throws IOException {
        Path path = Paths.get(getWorkingDirectory() + "/" + fileName);
        return Files.readAllBytes(path);
    }

    public String[] getFilesInDirectory(String path) {
        return new File(path).list();
    }

    public String getFileName(String path) throws InvalidPathException {
        Pattern p = Pattern.compile("([^/]*)$");
        Matcher matcher = p.matcher(path);
        if (matcher.find()) {
            return matcher.group(0);
        } else  {
            throw new InvalidPathException("Invalid file path: " + path);
        }
    }

    public void createFile(String path, byte[] data) throws IOException, InvalidPathException {
        Path file = Paths.get(getWorkingDirectory() + "/" + getFileName(path));
        Files.write(file, data);
    }

    public boolean deleteFile(String path) throws IOException, InvalidPathException {
        Path pathToFile = Paths.get(getWorkingDirectory() + "/" + getFileName(path));
        return Files.deleteIfExists(pathToFile);
    }
}