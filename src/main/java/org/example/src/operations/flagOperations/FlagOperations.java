package org.example.src.operations.flagOperations;

import java.util.List;

public class FlagOperations {
    // has not started working on yet
    public static void openFlag(List<String> args) {
        //Open open = new Open(args);
    }

    public static void moveFlag(List<String> args) {
        new Move(args).execute();
    }

    public static void deleteFlag(List<String> args) {
        new Delete(args).execute();
    }

    public static void renameFlag(List<String> args) {
        new Rename(args).execute();
    }
}
