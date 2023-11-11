package org.example.src.operations.flagOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.constants.PathConstants;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.example.src.constants.KeyWords.generateKeywordToPathMapping;

/**
 * TO USE THIS CLASS DURING PROGRAM:
 * pass -move, then pass the name of the file you want to move, then pass the prefix of the destination
 * for example
 * -move filename gkQ   -> this moves the file name called 'filename' to 04-videos-and-screenshots/01-Knowledge-Transfer/04-general-knowledge
 * for moving multiple arguments just continue to pass the file name and keywords
 * for example
 * -move filename gkQ fileNameTwo ktI filenameThree tvB
 * <p>
 * for testing pass 'test' right after the -move flag
 * for example -move test filename gkQ, this will move the path from production path to test path
 */
public class Move {
    private static final Logger logger = LogManager.getLogger(Move.class);
    final private FileOperations fileOperations;
    final private Desktop desktop;
    private List<String> arguments;
    private final Utility utility = new Utility();

    public Move() {
        this.fileOperations = null;
        this.desktop = null;
    }

    public Move(List<String> arguments) {
        this.arguments =  arguments.subList(1, arguments.size()); // take off the -move or -m flag as its redundant
        this.fileOperations = new FileOperations();
        this.desktop = new Desktop(this.fileOperations);
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public void setArguments(List<String> newArguments) {
        this.arguments = newArguments;
    }

    public void execute() {
        if (getArguments().get(0).equals("test")) {
            assert this.desktop != null;
            this.desktop.setDesktopDir(PathConstants.TEST_DIRECTORY_PATH);

            List<String> args = getArguments();
            setArguments(args.subList(1, args.size()));
        }

        runThroughArgumentsAndMoveFiles(this.arguments);
        getArguments().clear();
    }

    private void runThroughArgumentsAndMoveFiles(List<String> args) {
        if (args.size() > 1) {
            assert this.desktop != null;
            boolean areDesktopFilesPresent = this.desktop.getAllFiles().isPresent();
            if (!areDesktopFilesPresent) logger.error("Desktop file is not present");
            else {
                File[] allArguments = this.desktop.getAllFiles().get();
                String stringifyPath = "";
                String fileToMatch = args.get(0); // this is the first element of arguments which represents the fileName that user wants to move

                for (File individualFile : allArguments) {
                    String fileName = individualFile.getName();
                    if (fileName.equalsIgnoreCase(fileToMatch)) {
                        stringifyPath = String.valueOf(individualFile);
                    }
                }

                String keyWord = args.get(1);

                Path targetPath = Path.of(stringifyPath).normalize();
                Path destinationPath = Path.of(generateKeywordToPathMapping().get(keyWord) + fileToMatch).normalize();

                try {
                    moveFile(targetPath, destinationPath);
                } catch (IOException e) {
                    logger.error("Can not move files because the directory or file does not exist");
                }

                setArguments(args.subList(2, args.size()));

                if (!this.arguments.isEmpty()) runThroughArgumentsAndMoveFiles(this.arguments);
            }
        } else logger.error("There is no keyword for this filename associated to file : " + this.arguments.get(0));
    }

    public void moveFileToDestination(File file, String keyword) throws IOException {
        Map<String, String> keywordToPathMap = generateKeywordToPathMapping();
        String relativePath = keywordToPathMap.get(keyword);

        String finalFileName = this.utility.stripKeyWord(file.getName());

        Path fullDestinationPath = Paths.get(PathConstants.ROOT_DIRECTORY + File.separator + relativePath + File.separator + finalFileName);
        Path targetPath = file.toPath();
        moveFile(targetPath, fullDestinationPath.normalize());
    }

    /**
     * @param startingPath    full path of the file/directory that you want to move
     * @param destinationPath full path of the destination of the file you want to move
     *                        ** make this work, you cant just have the full path to the destination,
     *                        you have to include the name of the file inside the directory destination
     */
    private void moveFile(Path startingPath, Path destinationPath) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPath.getFileName(), startingPath, destinationPath);
        logger.info(formattedString);
        Files.move(startingPath, destinationPath, REPLACE_EXISTING);
    }
}
