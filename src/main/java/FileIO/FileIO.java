package FileIO;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileIO {
    public String workingDirectory;

    public FileIO(String directory) {
        workingDirectory = directory;
    }

    public boolean exists(String path) {
        File fileToCheck = new File(getPath(path));
        System.out.println(fileToCheck.getAbsolutePath());
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
}
