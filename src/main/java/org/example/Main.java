package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.operations.CommandLineParser;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Desktop desktop = new Desktop(new FileOperations(new Utility()));
        CommandLineParser parser = new CommandLineParser();
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;
        boolean firstTry = true;

        while (continueProgram) {
            if (firstTry) desktop.pollDesktop();
            firstTry = false;

            String[] arguments = parser.splitArgument(scanner.nextLine().toLowerCase());
            continueProgram = parser.processArguments(arguments);
        }
    }
}
