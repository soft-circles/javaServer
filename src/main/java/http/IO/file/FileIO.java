package http.IO.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO implements IFileIO {
    private String workingDirectory;

    public FileIO(String path) {
        workingDirectory = path;
    }

    @Override
    public boolean exists(String path) {
        File fileToCheck = new File(getPath(path));
        return fileToCheck.exists();
    }

    @Override
    public boolean isFile(String path) {
        File fileToCheck = new File(getPath(path));
        return fileToCheck.isFile();
    }

    @Override
    public boolean isDirectory(String path) {
        File directoryToCheck = new File(getPath(path));
        return directoryToCheck.isDirectory();
    }

    private String getPath(String fileName) {
        return workingDirectory + fileName;
    }

    @Override
    public String getWorkingDirectory() {
        return workingDirectory;
    }

    @Override
    public byte[] readFile(String fileName) throws IOException {
        Path path = Paths.get(getWorkingDirectory() + "/" + fileName);
        return Files.readAllBytes(path);
    }

    @Override
    public byte[] readFile(String fileName, int start, int end) throws IOException {
        Path path = Paths.get(getWorkingDirectory() + "/" + fileName);
        byte[] fileBytes = Files.readAllBytes(path);
        String s = new String(fileBytes, "UTF-8");
        String substring = s.substring(start, end);
        return substring.getBytes();
    }

    @Override
    public String[] getFilesInDirectory(String path) {
        return new File(workingDirectory + path).list();
    }

    @Override
    public void createFile(String path, byte[] data) throws IOException {
        Path file = Paths.get(workingDirectory + path);
        Files.write(file, data);
    }

    @Override
    public boolean deleteFile(String path) throws IOException {
        Path pathToFile = Paths.get(getWorkingDirectory() + path);
        return Files.deleteIfExists(pathToFile);
    }


}