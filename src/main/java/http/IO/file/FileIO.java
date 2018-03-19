package http.IO.file;

import java.io.File;

public class FileIO {
    public String workingDirectory;

    public FileIO(String directory) {
        workingDirectory = directory;
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
}
