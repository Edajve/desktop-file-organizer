package org.example.src.operations.flagOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.operations.FileOperations;

import java.util.List;

public class FlagOperations {
    private FileOperations fileOperations = new FileOperations();
    private static final Logger logger = LogManager.getLogger(FlagOperations.class);

    public static void openFlag(List<String> args, String applicationToOpenIn) {
        Open open = new Open(args);

        if (open.canOpenInApplication(applicationToOpenIn)) {
            // command to test opening a file in preview '-open Screen Shot 2023-10-23 at 08.40.51 AM [1] Preview'
            List<String> filesToOperateOn = args.subList(1, args.size() - 1);
            try {
                for (String fileName : filesToOperateOn) {
                    logger.info("opening file '" + fileName + "' in application '" + applicationToOpenIn + "'");
                    new ProcessBuilder("open", "-a", applicationToOpenIn, fileName).start();
                }
            } catch (Exception exception) {
                logger.error("Error opening file", exception);
            }
        } else {
            // this is not tested yet
            // also needs to add light for not opening this application
            String format = String.format("Can not open file using application '%s'", applicationToOpenIn);
            logger.error(format);
        }
    }

    public static void showFlag(List<String> args) {
        Show show = new Show();
    }

    public static void deleteFlag(List<String> args) {
        Delete delete = new Delete();
    }
}
