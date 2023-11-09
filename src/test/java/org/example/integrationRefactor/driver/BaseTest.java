package org.example.integrationRefactor.driver;

import org.example.Main;
import org.example.src.constants.PathConstants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

public abstract class BaseTest {

    private static final File desktopPath = new File(PathConstants.DESKTOP_PATH);
    private static Thread mainThread;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        Thread.sleep(1500);
        // create the proper directory for which all the integration tests will run in
        if (!desktopPath.exists() || !desktopPath.isDirectory())
            System.out.println("desktop file does not exist");

        boolean directoryWasMade = new File(PathConstants.TEST_DIRECTORY_PATH).mkdir();
        if (directoryWasMade) System.out.println("Directory created on desktop: " + PathConstants.TEST_FOLDER_NAME);
        else System.out.println("Failed to create directory because it has already been created");
    }

    @AfterAll
    public static void cleanUp() throws InterruptedException {
        // Code that runs once after all tests in every test class that extends BaseTest
        Driver.cleanOutTestDirectory();
        if (mainThread != null && mainThread.isAlive()) {
            Main.stopProgram();
            mainThread.join(); // Wait for the main program to finish
        }
    }
}
