package org.example.src.operations.flagOperations;

import java.util.List;

public class FlagOperations {
    // has not started working on yet
    public static void openFlag(List<String> args) {
        //Open open = new Open(args);
    }

    // has not started working on yet
    public static void showFlag(List<String> args) {
        // Move move = new Move();
    }

    public static void deleteFlag(List<String> args) {
        new Delete(args).execute();
    }
}
