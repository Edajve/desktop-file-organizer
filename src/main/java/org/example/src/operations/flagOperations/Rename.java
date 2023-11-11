package org.example.src.operations.flagOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.Scanners.Desktop;
import org.example.src.constants.PathConstants;
import org.example.src.operations.FileOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * TO USE THIS CLASS DURING PROGRAM:
 * Single renames:
 * pass -r the name of the file you want to rename and then the actual name you want to rename it to
 * Ex -r file newFileName
 * <p>
 * Multiple renames:
 * pass -r the name of the file you want to rename and then the actual name you want to rename it to
 * then do the same thing for next file
 * * Ex -r file newFileName file2 newFileName2
 */
public class Rename {
    private List<String> arguments;
    private Desktop desktop;
    private final FileOperations fileOperations;
    private static final Logger logger = LogManager.getLogger(Rename.class);

    public Rename(List<String> arguments) {
        this.arguments = arguments.subList(1, arguments.size()); // take off the -rename or -r flag as its redundant
        this.fileOperations = new FileOperations();
        this.desktop = new Desktop(this.fileOperations);
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void execute() {

        if (getArguments().get(0).equals("test")) {
            assert this.desktop != null;
            this.desktop.setDesktopDir(PathConstants.TEST_DIRECTORY_PATH);

            List<String> args = getArguments();
            setArguments(args.subList(1, args.size()));
        }


        runThroughArgumentsAndReplaceNames(this.arguments);
        getArguments().clear();
    }

    private void runThroughArgumentsAndReplaceNames(List<String> args) {
        if (args.size() > 1) {
            List<String> arguments = getArguments();
            String originalName = args.get(0);
            String whatFileIsGoingToBeNamed = args.get(1);

            Optional<File[]> allFiles = this.desktop.getAllFiles();
            String targetPath = "";

            if (allFiles.isPresent()) {

            for (int i = 0; i < allFiles.get().length; i++) {
                File file = allFiles.get()[i];
                if (file.getName().equals(originalName)) {
                    targetPath = String.valueOf(file);
                    break;
                }
            }

            String destinationPath = replacePathWithNewName(targetPath, whatFileIsGoingToBeNamed);

            try {
                renameFile(Path.of(targetPath).normalize(), Path.of(destinationPath).normalize());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            setArguments(args.subList(2, args.size()));

            if (getArguments().size() == 1)
                logger.error("You will need to add an argument to rename this file of: " + getArguments().get(0));
            else if (!getArguments().isEmpty())
                runThroughArgumentsAndReplaceNames(this.arguments);

            } else logger.error("This directory does not exist");
        } else logger.error("There is no keyword for this filename associated to file : " + this.arguments.get(0));
    }

    public String replacePathWithNewName(String startingPath, String replacingName) {
        String[] arrayOfDirectories = startingPath.split("/");
        arrayOfDirectories[arrayOfDirectories.length - 1] = replacingName;
        return String.join("/", arrayOfDirectories);
    }

    /**
     * @param startingPath    full path of the file/directory that you want to rename
     * @param destinationPath full path of the destination of the file you want to rename
     *                        ** make this work, you cant just have the full path to the destination,
     *                        you have to include the name of the file inside the directory destination
     */
    private void renameFile(Path startingPath, Path destinationPath) throws IOException {
        String formattedString = String.format("File '%s' at path '%s' was moved to path '%s'...", startingPath.getFileName(), startingPath, destinationPath);
        logger.info(formattedString);
        Files.move(startingPath, destinationPath, REPLACE_EXISTING);
    }
}
