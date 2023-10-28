package org.example.src.operations.flagOperations;

import java.util.List;

public class FlagOperations {
    private FileOperations fileOperations = new FileOperations();
    private static final Logger logger = LogManager.getLogger(FlagOperations.class);

    public static void openFlag(List<String> args, String applicationToOpenIn) {
        Open open = new Open(args);
        System.out.println(open.getArguments());
    }

    public static void showFlag(List<String> args) {
        Show show = new Show();
    }

    public static void deleteFlag(List<String> args) {
        Delete delete = new Delete();
    }
}
