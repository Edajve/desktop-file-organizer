package org.example.integration.steps;

import io.cucumber.java.en.Given;
import org.example.src.constants.PathConstants;

import java.io.File;

public class Hooks {
    private static final File desktopPath = new File(PathConstants.DESKTOP_PATH);

    @Given("There is a desktop folder named {string}")
    public void thereIsADesktopFolderNamed(String folderName) {
        // create the proper directory for which all the integration tests will run in
        System.out.println("setting up --------------");
        if (!desktopPath.exists() || !desktopPath.isDirectory())
            System.out.println("desktop file does not exist");

        boolean directoryWasMade = new File(PathConstants.TEST_DIRECTORY_PATH).mkdir();
        if (directoryWasMade) System.out.println("Directory created on desktop: " + PathConstants.TEST_FOLDER_NAME);
        else System.out.println("Failed to create directory because it has already been created");
    }
}
