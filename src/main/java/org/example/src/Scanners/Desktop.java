package org.example.src.Scanners;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
        String destinationPathString = determineDestinationPath(keyword, file.getName());
        Path targetPath = file.toPath();
        Path destinationPath = Paths.get(destinationPathString);

        moveFolder(targetPath, destinationPath);
    }

    private static String determineDestinationPath(String keyword, String fileName) {
        String pathFromKeyword = takeKeyWordReturnPath(keyword);
        return DirectoryPaths.ROOT_DIRECTORY + File.separator + pathFromKeyword + File.separator + fileName;
    }

    private static String takeKeyWordReturnPath(String keyWord) {
        switch (keyWord) {
            case "TfP":
                return "05-test-for-program";
            case "pwdX":
                return "01-personal-work-documents";
            case "pA":
                return "02-work-documents/01-Onboarding/01-Permissions";
            case "tvB":
                return "02-work-documents/02-Terms-and-Vocab";
            case "coC":
                return "02-work-documents/03-Chicago-Office";
            case "kF":
                return "02-work-documents/04-screenshots/01-for-learning/01-Kibana";
            case "ddG":
                return "02-work-documents/04-screenshots/01-for-learning/02-Data-dog";
            case "vddK":
                return "04-videos-and-screenshots/01-Knowledge-Transfer/01-external-software-tools/01-VideoDataDog";
            case "vtmL":
                return "04-videos-and-screenshots/01-Knowledge-Transfer/01-external-software-tools/02-VideoTestmo";
            case "scN":
                return "04-videos-and-screenshots/01-Knowledge-Transfer/02-vivid-seats-code/01-skybox-client";
            case "ssO":
                return "04-videos-and-screenshots/01-Knowledge-Transfer/02-vivid-seats-code/02-skybox-services";
            case "kttP":
                return "04-videos-and-screenshots/01-Knowledge-Transfer/03-knowledgeTransferTesting";
            case "gkQ":
                return "04-videos-and-screenshots/01-Knowledge-Transfer/04-general-knowledge";
            case "trash":
                return "trash";
            default:
                logger.error("No keyword was found for this file");
        }
        return null;
    }

    private static void moveFolder(Path startingPoint, Path destination) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPoint.getFileName(), startingPoint, destination);
        logger.info(formattedString);
        Files.move(startingPoint, destination, REPLACE_EXISTING);
    }
}
