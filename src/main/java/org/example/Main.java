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

        while (true) {
            desktop.pollDesktop();

            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                logger.info("Exiting program...");
                break;
            } else if (input.equalsIgnoreCase("something")) {
                logger.info("passed something else");
            } else  {
                String format = String.format("Argument '%s' is not recognized in this program...", input);
                logger.info(format);
                break;
            }
        }

        scanner.close();
    }
}

