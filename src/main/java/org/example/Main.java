package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.operations.CommandLineParser;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.util.Scanner;

public class Main {
    private static volatile boolean continueProgram = true; // volatile to ensure thread visibility
    private static Desktop desktop;
    private static CommandLineParser parser;
    private static Scanner scanner;

    public static void main(String[] args, PipedInputStream pipedIn) throws IOException {
        System.out.println("Started-----");
        desktop = new Desktop(new FileOperations(new Utility()));
        parser = new CommandLineParser();

        // Check if the pipedIn argument has been passed, otherwise use System.in
        InputStream inputStream = args.length > 1 ? new ByteArrayInputStream(args[1].getBytes()) : System.in;
        scanner = new Scanner(inputStream);

        if (args.length > 0) desktop.setDesktopDirectory(args[0]);

        setupShutdownHook();

        // If a PipedInputStream is passed, use it as input
        if (pipedIn != null) {
            scanner = new Scanner(pipedIn);
        }

        runCommandLoop(scanner);
    }

    private static void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopProgram();
            System.out.println("Shutting down...");
        }));
    }

    private static void runCommandLoop(Scanner inputScanner) {
        boolean firstTry = true;

        try {
            while (continueProgram) {
                if (firstTry) {
                    desktop.pollDesktop();
                    firstTry = false;
                }

                if (inputScanner.hasNextLine()) {
                    String[] arguments = parser.splitArgument(inputScanner.nextLine().toLowerCase());
                    continueProgram = parser.processArguments(arguments);
                } else {
                    continueProgram = false; // Stop if there are no more lines to read (e.g., EOF or stream closed)
                }
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        } finally {
            inputScanner.close();
            desktop.cleanUp();
        }
    }

    public static void stopProgram() {
        continueProgram = false;
    }
}
