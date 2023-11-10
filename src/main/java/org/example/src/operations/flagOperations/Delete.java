package org.example.src.operations.flagOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.constants.Ignore;
import org.example.src.constants.PathConstants;
import org.example.src.operations.FileOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Delete {
    private static final Logger logger = LogManager.getLogger(Delete.class);
    private FileOperations fileOperations;
    private Desktop desktop;
    private List<String> arguments;

    public Delete() {
        this.fileOperations = null;
        this.desktop = null;
    }

    public Delete(List<String> arguments) {
        this.arguments = arguments;
        this.fileOperations = new FileOperations();
        this.desktop = new Desktop(this.fileOperations);
    }

    // Constructor for testing purposes
    public Delete(List<String> arguments, Desktop desktop) {
        this.arguments = arguments;
        this.fileOperations = new FileOperations();
        this.desktop = desktop;
    }

    public void execute() {
        List<String> arguments = getArguments();
        List<String> filesToDelete = arguments.subList(1, arguments.size());

        for (String argument : filesToDelete) {
            if (argument.equalsIgnoreCase("all")) {
                handleDeleteAllFiles();
                break;
            } else if (argument.equalsIgnoreCase("allTest")) {
                this.desktop.setDesktopDirectory(PathConstants.TEST_DIRECTORY_PATH);
                handleDeleteAllFiles();
                break;
            } else {
                handleIndividualFileDeletion(argument);
            }
        }

        clear();
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    private void handleDeleteAllFiles() {
        Optional<File[]> allFiles = this.desktop.getAllFiles();
        if (!allFiles.isPresent()) {
            logger.error("There are no files on the desktop");
        } else {
            deleteAllFiles(allFiles.get());
        }
    }

    private void handleIndividualFileDeletion(String argument) {
        if (argument.contains("testie")) {
            this.desktop.setDesktopDirectory(PathConstants.TEST_DIRECTORY_PATH);
        }
        String path = this.desktop.getPath(argument);
        deleteFile(new File(path));
    }

    private void deleteAllFiles(File[] allFiles) {
        Arrays.stream(allFiles).forEach(file -> {
            if (!Ignore.DirectoryName.DIRECTORIES_TO_IGNORE.contains(file.getName())) {
                deleteFile(file);
            }
        });
    }

    public void deleteFile(File file) {
        boolean wasDeleted = file.delete();
        if (wasDeleted) {
            logger.info("File " + file.getName() + " was deleted successfully...");
        } else {
            logger.info("Failed to delete the file " + file.getName() + "....");
        }
    }

    private void clear() {
        this.arguments = new ArrayList<>();
    }
}
