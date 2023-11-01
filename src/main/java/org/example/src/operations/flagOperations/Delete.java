package org.example.src.operations.flagOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.constants.Ignore;
import org.example.src.operations.FileOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Delete {
    private static final Logger logger = LogManager.getLogger(Delete.class);
    final private FileOperations fileOperations;
    final private Desktop desktop;
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

    public List<String> getArguments() {
        return this.arguments;
    }

    private void clear() {
        this.arguments = new ArrayList<>();
    }

    public void execute() {
        List<String> filesToDelete = getArguments().subList(1, getArguments().size());
        for (String argument : filesToDelete) {
            if (argument.equalsIgnoreCase("all")) {
                Optional<File[]> allFiles = this.desktop.getAllFiles();
                if (allFiles.isEmpty()) logger.error("There are no files on the desktop");

                deleteAllFiles(allFiles);
                break;
            } else {
                String path = this.desktop.getPath(argument);
                deleteFile(new File(path));
            }
        }
        clear();
    }

    private void deleteAllFiles(Optional<File[]> allFiles) {
        Arrays.stream(allFiles.get()).forEach(file -> {
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
}
