package org.example.src.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.AllowedArguments;
import org.example.src.operations.flagOperations.FlagOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * example of how this program expects arguments
 * -delete all
 * -delete all folders
 * -delete all files
 * -delete {file/folder name}
 * <p>
 * -open {Input file}
 * -open {file} {application}
 */
public class CommandLineParser {
    final private List<String> arguments;
    private static final Logger logger = LogManager.getLogger(CommandLineParser.class);

    public CommandLineParser() {
        this.arguments = new ArrayList<>();
    }

    public void addArgument(String arg) {
        this.arguments.add(arg);
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public void clear() {
        this.arguments.clear();
    }

    public String[] splitArgument(String args) {
        if (args.contains("\"")) {
            // have to handle a case where an argument(s)
            // is wrapped in quotes because the name of a file has spaces
            // ideally it should be renamed before passed but this is an edge case
            System.out.println(args);
            String[] a = args.split(" ");
            List<String> properCommand = new ArrayList<>();
            return properCommand.toArray(new String[0]);
        }
        return args.split(" ");
    }

    public boolean processArguments(String[] arguments) {
        boolean continueProgram;
        if (arguments.length == 1) continueProgram = handleSingleArgument(arguments[0]);
        else continueProgram = handleMultipleArguments(arguments);
        return continueProgram;
    }

    private boolean handleSingleArgument(String argument) {
        boolean continueProgram = true;
        boolean isFlagWithNoArguments = AllowedArguments.arguments.contains(argument);

        if (argument.equalsIgnoreCase("exit")) {
            logger.info("Exiting program...");
            continueProgram = false;
        } else if (isFlagWithNoArguments) logger.info(("This flag expects additional arguments..."));
        else {
            logger.info(String.format("Argument '%s' is not recognized in this program...", argument));
            continueProgram = false;
        }

        return continueProgram;
    }

    private boolean handleMultipleArguments(String[] arguments) {
        boolean continueProgram = true;

        if (Arrays.asList(arguments).contains("exit".toLowerCase())) {
            String[] deletedArgumentsAfterExit = deleteArgumentsAfterExit(arguments);
            Arrays.stream(deletedArgumentsAfterExit).forEach(a -> {
                if (a != null) this.addArgument(a);
            });
        } else if (AllowedArguments.arguments.contains(arguments[0]))
            Arrays.stream(arguments).forEach(this::addArgument);
        else {
            String format = String.format("Argument '%s' is not recognized in this program...", arguments[0]);
            logger.info(format);
            continueProgram = false;
        }

        if (continueProgram) executeArguments(this.arguments);
        return continueProgram;
    }

    private static void executeArguments(List<String> arguments) {
        switch (arguments.get(0)) {
            case "-open", "-o":
                // This flag requires an application name to open the files in,
                // application argument should be at the end of command
                int lastArgumentIndex = arguments.size() - 1;
                FlagOperations.openFlag(arguments, arguments.get(lastArgumentIndex));
                break;
            case "-delete", "-d":
                FlagOperations.deleteFlag(arguments);
                break;
            case "-show", "-s":
                FlagOperations.showFlag(arguments);
                break;
        }
    }

    private String[] deleteArgumentsAfterExit(String[] args) {
        String[] remainingArgs = new String[args.length];
        for (int i = 0; i < remainingArgs.length; i++) {
            if (args[i].equalsIgnoreCase("exit")) {
                remainingArgs[i] = args[i];
                break;
            }
            remainingArgs[i] = args[i];
        }
        return remainingArgs;
    }
}
