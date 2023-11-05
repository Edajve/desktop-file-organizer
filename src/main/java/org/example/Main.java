package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.operations.CommandLineParser;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static volatile boolean continueProgram = true; // volatile to ensure thread visibility
    private static Desktop desktop;
    private static CommandLineParser parser;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        System.out.println("Started-----");
        desktop = new Desktop(new FileOperations(new Utility()));
        parser = new CommandLineParser();
        scanner = new Scanner(System.in);

        if (args.length > 0) desktop.setDesktopDirectory(args[0]);

        setupShutdownHook();

        runCommandLoop();
    }

    private static void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopProgram();
            System.out.println("Shutting down...");
        }));
    }

    private static void runCommandLoop() {
        boolean firstTry = true;

        try {
            while (continueProgram) {
                if (firstTry) {
                    desktop.pollDesktop();
                    firstTry = false;
                }

                if (scanner.hasNextLine()) {
                    String[] arguments = parser.splitArgument(scanner.nextLine().toLowerCase());
                    continueProgram = parser.processArguments(arguments);
                } else {
                    continueProgram = false; // Stop if there are no more lines to read (e.g., EOF or stream closed)
                }
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        } finally {
            scanner.close();
            desktop.cleanUp();
        }
    }

    public static void stopProgram() {
        continueProgram = false;
    }
}
