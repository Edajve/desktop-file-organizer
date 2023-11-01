package org.example.tests;

import org.example.src.Scanners.Desktop;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesktopTest {

    Desktop underTest = new Desktop(new FileOperations(new Utility()));


    @Test
    public void populatePrivateMemberIfDoesNotMatch_WhenListMatch_ExistingListShouldNotUpdate() {
        // Given
        List<File> current = new ArrayList<>();
        List<File> existing = new ArrayList<>();

        current.add(new File("A"));
        current.add(new File("B"));
        current.add(new File("C"));

        existing.add(new File("A"));
        existing.add(new File("B"));
        existing.add(new File("C"));

        // When
        underTest.populatePrivateMemberIfDoesNotMatch(current, existing);

        // Then
        assertEquals(
                existing,
                List.of(
                        new File("A"),
                        new File("B"),
                        new File("C")
                ));
    }

    @Test
    public void populatePrivateMemberIfDoesNotMatch_WhenListAreNotMatch_ExistingListShouldUpdate() {
        // Given
        List<File> current = new ArrayList<>();
        List<File> existing = new ArrayList<>();

        current.add(new File("A"));

        existing.add(new File("X"));
        existing.add(new File("Y"));
        existing.add(new File("Z"));

        // When
        underTest.populatePrivateMemberIfDoesNotMatch(current, existing);

        // Then
        assertEquals(List.of(new File("A")), existing);
    }
}
