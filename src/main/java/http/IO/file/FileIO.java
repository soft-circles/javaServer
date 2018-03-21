package http.IO.file;

import java.io.File;

public class FileIO {
    private static String workingDirectory;

    public FileIO() {
        workingDirectory = "../cob_spec/public";
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

    public void setWorkingDirectory(String path) {
        this.workingDirectory = path;
    }


}
