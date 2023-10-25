package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.constants.AllowedArguments;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        Desktop desktop = new Desktop(new FileOperations(new Utility()));
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        boolean firstTry = true;

        while (continueLoop) {

            if (firstTry) desktop.pollDesktop();
            String[] arguments = scanner.nextLine().split(" ");
            continueLoop = handleArguments(arguments); // logic for this method does not work yet
            firstTry = false;
        }
    }

    private static boolean handleArguments(String[] arguments) {
        boolean endProgram = false;

        if (arguments.length == 1) {

            if (arguments[0].equalsIgnoreCase("exit")) {
                logger.info("Exiting program...");
            } else if (AllowedArguments.arguments.contains(arguments[0])) {
                logger.info(("This flag expects additional arguments..."));
                endProgram = true;
            } else {
                String format = String.format("Argument '%s' is not recognized in this program...", arguments[0]);
                logger.info(format);
            }

        } else {

            for (String argument : arguments) {
                if (argument.equalsIgnoreCase("exit")) {
                    logger.info("Exiting program...");
                    break;
                } else if (AllowedArguments.arguments.contains(arguments[0])) {
                    /*
                    Figure out the logic or requirements for this block
                    If you're in this block of code that means that the first argument is allowed
                    and there is another argument coming after the first argument.

                    you have to come up with a method to be able to gather all the arguments before you do the operation
                    so come up with a way, either store it in a list and then after the loop has
                    exhausted run a method on all the arguments. or something else if you think of

                    by doing it this way, you are exhausting the whole loop before running the method
                    so that you don't have to worry about dealing with after effects of the loop
                     */
                    logger.info("passed something else");
                    endProgram = true;
                } else {
                    String format = String.format("Argument '%s' is not recognized in this program...", argument);
                    logger.info(format);
                    break;
                }
            }
        }
        return endProgram;
    }
}

