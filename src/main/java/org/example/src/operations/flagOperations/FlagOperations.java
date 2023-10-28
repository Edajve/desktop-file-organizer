package org.example.src.operations.flagOperations;

import java.util.List;

public class FlagOperations {
    public static void openFlag(List<String> args) {
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
