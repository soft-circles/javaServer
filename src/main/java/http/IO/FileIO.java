package http.IO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
        File file = new File(workingDirectory + path);
        return file.list((dir, name) -> !name.startsWith("."));
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