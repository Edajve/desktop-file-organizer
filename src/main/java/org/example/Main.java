package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        Desktop desktop = new Desktop(new FileOperations(new Utility()));
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            desktop.pollDesktop();
            String[] arguments = scanner.next().split(" ");
            continueLoop = handleArguments(arguments);
        }
    }

    public static boolean handleArguments(String[] arguments) {
        boolean returnVal = false;

        for (String argument : arguments) {
            if (argument.equalsIgnoreCase("exit")) {
                logger.info("Exiting program...");
                returnVal = false;
            } else if (argument.equalsIgnoreCase("something")) {
                logger.info("passed something else");
                returnVal = true;
            } else {
                String format = String.format("Argument '%s' is not recognized in this program...", argument);
                logger.info(format);
                returnVal = false;
            }
        }
        return returnVal;
    }
}

