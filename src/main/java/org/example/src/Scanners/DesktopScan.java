package org.example.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            if (!(file.getName().equals(".DS_Store")) &&
                    !(file.getName().equals(".localized"))
            ) {
                currentDesktopState.add(file);
            }
        }

        populatePrivateMemberIfDoesNotMatch(currentDesktopState, desktopFiles);
    }

    static void populatePrivateMemberIfDoesNotMatch(List<File> current, List<File> existing) {
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
}
