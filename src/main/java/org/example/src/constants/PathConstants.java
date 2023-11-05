package org.example.src.constants;

import java.io.File;

public class PathConstants {

    // Stage constants
    public final static String DESKTOP_PATH = File.separator + "Users" + File.separator + "dajve.echols" + File.separator + "Desktop";
    // This should be the parent directory  of where your file structure starts
    public final static String ROOT_DIRECTORY = File.separator + "Users" + File.separator + "dajve.echols" + File.separator + "file-structure" + File.separator + "01-vivid-seats";
    public final static String DESKTOP_TEST_PATH =
            File.separator +
                    "Users"
                    + File.separator
                    + "dajve.echols"
                    + File.separator
                    + "file-structure"
                    + File.separator
                    + "03-personal"
                    + File.separator
                    + "02-for-storage"
                    + File.separator
                    + "testing-directory-for-automatic-desktop-organizer";

    // Test constants
    public final static String TEST_FOLDER_NAME = "integration-test-directory";
    public final static String TEST_DIRECTORY_PATH = PathConstants.DESKTOP_PATH + File.separator + TEST_FOLDER_NAME;
}
