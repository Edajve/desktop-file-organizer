package org.example.integration.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.example.src.constants.PathConstants;

import java.io.File;

public class BackGroundSteps {
//    private String createdDirectoryPath = PathConstants.DESKTOP_PATH;
//    private final File desktopDir = new File(PathConstants.DESKTOP_PATH);
//
//    public String getCreatedDirectoryPath() {
//        return createdDirectoryPath;
//    }
//
//    public void setCreatedDirectoryPath(String createdDirectoryPath) {
//        this.createdDirectoryPath = createdDirectoryPath;
//    }

    @Given("set up test")
    public static void set_up_test() {
        // Set up
        System.out.println("Set up here-------");
    }

//    @Given("There is a desktop folder named {string}")
//    public void thereIsADesktopFolderNamed(String folderName) {
////        setCreatedDirectoryPath(PathConstants.DESKTOP_PATH + File.separator + folderName);
//
//        if (!desktopDir.exists() || !desktopDir.isDirectory())
//            System.out.println("desktop file does not exist");
//
//        boolean directoryWasMade = new File(PathConstants.TEST_DIRECTORY_PATH).mkdir();
//        if (directoryWasMade) System.out.println("Directory created on desktop: " + folderName);
//        else System.out.println("Failed to create directory because it has already been created");
//    }
//
//    @And("two files named {string} and {string}")
//    public void twoFilesNamedAnd(String firstFileName, String secondFileName) throws Exception {
//        try {
//            File firstFile = new File(desktopDir + File.separator + secondFileName);
//            File secondFile = new File(desktopDir + File.separator + firstFileName);
//
//            boolean isFirstFileCreated = firstFile.createNewFile();
//            boolean isSecondFileCreated = secondFile.createNewFile();
//
//            if (isFirstFileCreated && isSecondFileCreated) System.out.println("Both files created successfully");
//            else System.out.println("Both files created NOT successfully because it has already been created");
//
//        } catch (Exception exception) {
//            throw new Exception(exception.getMessage());
//        }
//    }
}
