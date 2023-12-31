package org.example.src.constants;

import java.util.Arrays;

public class AllowedArguments {
    // the '-' before the argument means that there are additional
    // parameters that can be passed along with the original argument
    public static String arguments = Arrays.toString(new String[]{
            "-open",
            "-o",
            "-delete",
            "-d",
            "-move",
            "-m",
            "-rename",
            "-r"
    });
}
