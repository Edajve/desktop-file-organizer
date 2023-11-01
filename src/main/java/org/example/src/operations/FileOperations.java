package org.example.src.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.KeyWords;
import org.example.src.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileOperations {

    final private Utility utility;

    public FileOperations() {
        this.utility = null;
    }

    public FileOperations(Utility utility) {
        this.utility = utility;
    }

    private static final Logger logger = LogManager.getLogger(FileOperations.class);

    public void processFilesBasedOnCriteria(List<File> files) throws IOException {

        for (File file : files) {
            String keyword = this.utility.extractKeywordFromFileName(file);

            if ("trash".equals(keyword)) {
                deleteFile(file);
            } else {
                moveFileToDestination(file, keyword);
            }
        }
    }

    public void deleteFile(File file) {
        boolean wasDeleted = file.delete();
        if (wasDeleted) {
            logger.info("File " + file.getName() + " was deleted successfully...");
        } else {
            logger.info("Failed to delete the file " + file.getName() + "....");
        }
    }

    private void moveFileToDestination(File file, String keyword) throws IOException {
        Map<String, String> keywordToPathMap = KeyWords.generateKeywordToPathMapping();
        String relativePath = keywordToPathMap.get(keyword);

        String finalFileName = this.utility.stripKeyWord(file.getName());

        Path fullDestinationPath = Paths.get(DirectoryPaths.ROOT_DIRECTORY + File.separator + relativePath + File.separator + finalFileName);
        Path targetPath = file.toPath();
        moveFile(targetPath, fullDestinationPath);
    }

    private void moveFile(Path startingPath, Path destinationPath) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPath.getFileName(), startingPath, destinationPath);
        logger.info(formattedString);
        Files.move(startingPath, destinationPath, REPLACE_EXISTING);
    }
}
