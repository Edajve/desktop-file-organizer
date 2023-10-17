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
import org.example.src.constants.Ignore;
import org.example.src.constants.KeyWords;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Desktop {
    private static final Logger logger = LogManager.getLogger(Desktop.class);
    private final String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
    private final File desktopDir = new File(desktopPath);
    private final List<File> desktopFiles = new ArrayList<>();
    private static FileStructure fileStructure;

    public Desktop() {
    }

    public Desktop(FileStructure fileStructure) {
        this.fileStructure = fileStructure;
    }

    public List<File> getDesktopFiles() {
        return this.desktopFiles;
    }

    public String getDesktopPath() {
        return desktopPath;
    }

    public File getDesktopDir() {
        return this.desktopDir;
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
        checkFilesWithKeyWords(current);
    }

    private static void checkFilesWithKeyWords(List<File> files) {
        String[] keyWords = KeyWords.KEYWORDS;
        List<File> filesToMove = new ArrayList<>();

        for (File file : files) {
            String fileName = file.getName();
            for (String keyWord : keyWords) {
                if (fileName.contains(keyWord)) {
                    filesToMove.add(file);
                }
            }
        }
        moveFilesBasedOnCriteria(filesToMove);
    }

    private static void moveFilesBasedOnCriteria(List<File> files) {
        try {
            fileStructure.scanFileStructure();
        } catch (Exception ignored) {
        }
    }

    private void moveFolder(Path startingPoint, Path destination) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPoint.getFileName(), startingPoint, destination);
        logger.info(formattedString);
        Files.move(startingPoint, destination, REPLACE_EXISTING);
    }
}
