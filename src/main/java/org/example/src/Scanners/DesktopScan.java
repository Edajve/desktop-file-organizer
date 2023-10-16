package org.example.src.Scanners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class DesktopScan {
    private static final Logger logger = LogManager.getLogger(DesktopScan.class);
    private final String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
    private final File desktopDir = new File(desktopPath);
    private static final List<File> desktopFiles = new ArrayList<>();

    public void scanDesktop() {

        if (!desktopDir.exists() || !desktopDir.isDirectory()) logger.info("Desktop directory does not exist..");

        File[] allFiles = desktopDir.listFiles();
        List<File> currentDesktopState = new ArrayList<>();

        if (allFiles == null) logger.info("There are no files on the desktop..");

        for (File file : allFiles) {
            if (!(file.getName().equals(Ignore.DirectoryName.DS_STORE)) &&
                    !(file.getName().equals(Ignore.DirectoryName.LOCALIZE))
            ) {
                currentDesktopState.add(file);
            }
        }

        populatePrivateMemberIfDoesNotMatch(currentDesktopState, desktopFiles);
    }

     public static void populatePrivateMemberIfDoesNotMatch(List<File> current, List<File> existing) {
        boolean areEqual = new HashSet<>(current).containsAll(existing) &&
                new HashSet<>(existing).containsAll(current) &&
                current.size() == existing.size();

        if (areEqual) {
            logger.info("Desktop has not changed...");
        } else {
            logger.info("Desktop has changed updating desktop state...");
            existing.clear();
            existing.addAll(current);
        }
    }

    public void moveFolder(Path startingPoint, Path destination) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPoint.getFileName(), startingPoint, destination);
        logger.info(formattedString);
        Files.move(startingPoint, destination, REPLACE_EXISTING);
    }
}
