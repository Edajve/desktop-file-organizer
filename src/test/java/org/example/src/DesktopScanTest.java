package org.example.src;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesktopScanTest {
    @Test
    public void populatePrivateMemberIfDoesNotMatch_WhenListMatch_ExistingListShouldNotUpdate() {
        // Given
        DesktopScan underTest = new DesktopScan();
        List<File> current = new ArrayList<>();
        List<File> existing = new ArrayList<>();

        current.add(new File("A"));
        current.add(new File("B"));
        current.add(new File("C"));

        existing.add(new File("A"));
        existing.add(new File("B"));
        existing.add(new File("C"));

        // When
        DesktopScan.populatePrivateMemberIfDoesNotMatch(current, existing);

        // Then
        assertEquals(
                existing,
                List.of(
                        new File("A"),
                        new File("B"),
                        new File("C")
                ));
    }
}