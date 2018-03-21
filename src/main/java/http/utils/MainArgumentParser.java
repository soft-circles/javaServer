package http.utils;

public class MainArgumentParser {
    private int portNumber;
    private String workingDirectory;

    public MainArgumentParser(String[] args) {
        portNumber = 5000;
        workingDirectory = "./";

        for (int i = 0; i < args.length; i++) {
            try {

                if (args[i].equals("-p")) {
                    portNumber = Integer.parseInt(args[i]);
                }
                if (args[i].equals("-d")) {
                    workingDirectory = args[i];
            }
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
