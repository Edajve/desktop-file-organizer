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
                } else if (argument.equalsIgnoreCase("something")) {
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

