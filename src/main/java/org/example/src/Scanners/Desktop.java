package org.example.src.Scanners;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;
import org.example.src.constants.KeyWords;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Desktop {
    private static final Logger logger = LogManager.getLogger(Desktop.class);
    private final File desktopDir = new File(DirectoryPaths.DESKTOP_PATH);
    private final List<File> desktopFiles = new ArrayList<>();
    private static FileStructure fileStructure;
    private static final Gson gson = new Gson();

    public Desktop() {
    }

    public Desktop(FileStructure fileStructure) {
        this.fileStructure = fileStructure;
    }

    public List<File> getDesktopFiles() {
        return this.desktopFiles;
    }

    public File getDesktopDir() {
        return this.desktopDir;
    }

    /**
     * Scans the desktop and adds all files to the private member variable 'desktopFile'
     *
     * @return
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

            for (File file : files) {
                processFileBasedOnCriteria(file);
            }
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    private static void processFileBasedOnCriteria(File file) throws IOException {
        String keyword = extractKeywordFromFileName(file);

        if ("trash".equals(keyword)) {
            deleteFile(file);
        } else {
            moveFileToDestination(file, keyword);
        }
    }

    private static String extractKeywordFromFileName(File file) {
        return file.getName().split("-")[0];
    }

    private static void deleteFile(File file) {
        boolean wasDeleted = file.delete();
        if (wasDeleted) {
            logger.info("File " + file.getName() + " was deleted successfully...");
        } else {
            logger.info("Failed to delete the file " + file.getName() + "....");
        }
    }

    private static void moveFileToDestination(File file, String keyword) throws IOException {
        Map<String, String> keywordToPathMap = KeyWords.generateKeywordToPathMapping();
        String relativePath = keywordToPathMap.get(keyword);

        Path fullDestinationPath = Paths.get(DirectoryPaths.ROOT_DIRECTORY + File.separator + relativePath + File.separator + file.getName());
        Path targetPath = file.toPath();
        moveFolder(targetPath, fullDestinationPath);
    }

    private static void moveFolder(Path startingPoint, Path destination) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPoint.getFileName(), startingPoint, destination);
        logger.info(formattedString);
        Files.move(startingPoint, destination, REPLACE_EXISTING);
    }
}
