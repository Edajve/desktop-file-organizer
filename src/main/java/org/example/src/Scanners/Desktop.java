package org.example.src.Scanners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.PathConstants;
import org.example.src.constants.Ignore;
import org.example.src.constants.KeyWords;
import org.example.src.operations.FileOperations;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Desktop {
    private static final Logger logger = LogManager.getLogger(Desktop.class);
    private File desktopDir = new File(PathConstants.DESKTOP_PATH);
    private final List<File> desktopFiles = new ArrayList<>();
    private final FileOperations fileOperations;

    public Desktop(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    public void setDesktopDirectory(String file) {
        this.desktopDir =  new File(file);
    }

    /**
     * Scans the desktop and operates on any file(s) that have a prefix,
     * it will choose the operation based on the prefix of the file
     */
    public void pollDesktop() throws IOException {

        if (!this.desktopDir.exists() || !this.desktopDir.isDirectory()) {
            logger.error("Desktop directory does not exist..");
        }

        Optional<File[]> allFiles = getAllFiles();
        List<File> currentDesktopState = new ArrayList<>();

        if (allFiles.isEmpty()) logger.info("There are no files on the desktop..");

        assert allFiles.isPresent();
        for (File file : allFiles.get()) {
            if (!Ignore.DirectoryName.DIRECTORIES_TO_IGNORE.contains(file.getName())) {
                currentDesktopState.add(file);
            }
        }

        populatePrivateMemberIfDoesNotMatch(currentDesktopState, this.desktopFiles);
        List<File> filesToMove = checkFilesWithKeyWords(this.desktopFiles);
        this.fileOperations.processFilesBasedOnCriteria(filesToMove);
    }

    public void populatePrivateMemberIfDoesNotMatch(List<File> current, List<File> localDesktopMember) {
        boolean areEqual = new HashSet<>(current).containsAll(localDesktopMember) &&
                new HashSet<>(localDesktopMember).containsAll(current) &&
                current.size() == localDesktopMember.size();

        if (areEqual) {
            logger.info("Desktop has not changed...");
        } else {
            logger.info("Desktop has changed updating desktop state...");
            localDesktopMember.clear();
            localDesktopMember.addAll(current);
        }
    }

    private List<File> checkFilesWithKeyWords(List<File> files) {
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
        return filesToMove;
    }

    public String getPath(String fileName) {
        return this.desktopDir + File.separator + fileName;
    }

    public Optional<File[]> getAllFiles() {
        return Optional.ofNullable(this.desktopDir.listFiles());
    }
}