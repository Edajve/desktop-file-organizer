package org.example.src.constants;

import java.util.Arrays;

public class AllowedArguments {
    // the -before the argument means that there are additional
    // parameters that can be passed along with the original argument
    String arguments = Arrays.toString(new String[]{
            "exit",
            "-open",
            "-delete"
    });
}
