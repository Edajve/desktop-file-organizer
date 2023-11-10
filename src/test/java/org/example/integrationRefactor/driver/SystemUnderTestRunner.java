package org.example.integrationRefactor.driver;

import org.example.Main;
import org.example.src.constants.PathConstants;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class SystemUnderTestRunner {
    private final PipedInputStream pipedIn;
    private final PipedOutputStream pipedOut;

    public SystemUnderTestRunner() throws IOException {
        pipedOut = new PipedOutputStream();
        pipedIn = new PipedInputStream(pipedOut);
    }

    public void runSystemUnderTest() {
        Thread mainThread = new Thread(() -> {
            // Pass the piped input stream to main
            Main.entry(new String[]{PathConstants.TEST_DIRECTORY_PATH}, pipedIn);
        });
        mainThread.start();
    }

    public void sendCommandToSystemUnderTest(String command) throws IOException {
        // Write the command followed by a newline, to simulate the user pressing "Enter"
        pipedOut.write((command + System.lineSeparator()).getBytes());
        pipedOut.flush();
    }
}
