package org.example.src.Scanners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;
import org.example.src.constants.KeyWords;
import org.example.src.operations.FileOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Desktop {
    private static final Logger logger = LogManager.getLogger(Desktop.class);
    private final File desktopDir = new File(DirectoryPaths.DESKTOP_PATH);
    private final List<File> desktopFiles = new ArrayList<>();
    private final FileOperations fileOperations;

    public Desktop(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    /**
     * Scans the desktop and adds all files to the private member variable 'desktopFile'
     */
    public void pollDesktop() {

        if (!this.desktopDir.exists() || !this.desktopDir.isDirectory())
            logger.info("Desktop directory does not exist..");

        File[] allFiles = this.desktopDir.listFiles();
        List<File> currentDesktopState = new ArrayList<>();

        if (allFiles == null) logger.info("There are no files on the desktop..");

        assert allFiles != null;
        for (File file : allFiles) {
            if (!(file.getName().equals(Ignore.DirectoryName.DS_STORE)) &&
                    !(file.getName().equals(Ignore.DirectoryName.LOCALIZE) &&
                            !(file.getName().equals(Ignore.DirectoryName.DONT_DELETE)))
            ) {
                currentDesktopState.add(file);
            }
        }
        populatePrivateMemberIfDoesNotMatch(currentDesktopState, this.desktopFiles);
    }

    public void populatePrivateMemberIfDoesNotMatch(List<File> current, List<File> existing) {
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
        checkFilesWithKeyWords(current);
    }

    private void checkFilesWithKeyWords(List<File> files) {
        Map<String, String> mapOfKeyWordsAndPaths = KeyWords.generateKeywordToPathMapping();
        List<File> filesToMove = new ArrayList<>();

        for (File file : files) {
            String fileName = file.getName();
            for (String keyWord : mapOfKeyWordsAndPaths.keySet()) {
                if (fileName.contains(keyWord)) {
                    filesToMove.add(file);
                }
            }
        }
        moveFilesBasedOnCriteria(filesToMove);
    }

    private void moveFilesBasedOnCriteria(List<File> files) {
        try {
            for (File file : files) {
                this.fileOperations.processFileBasedOnCriteria(file);
            }
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }
}