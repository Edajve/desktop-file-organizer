package org.example.tests;

import org.example.src.Scanners.Desktop;
import org.example.src.constants.DirectoryPaths;
import org.example.src.operations.FileOperations;
import org.example.src.operations.flagOperations.Delete;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {
    Delete underTest;

    @Test
    public void execute_delete_all_should_delete_all_files() {
        // Given
        Desktop desktop = new Desktop(new FileOperations());
        desktop.setDesktopDirectory(DirectoryPaths.DESKTOP_TEST_PATH);
        List<String> arguments = List.of("-d", "all");
        underTest = new Delete(arguments, desktop);

        // When
        underTest.execute();

        // Then
        Optional<File[]> allFiles = desktop.getAllFiles();

        int actual = Arrays.asList(allFiles.get()).size();
        int expected = 3;

        assertEquals(actual, expected);
    }
}
