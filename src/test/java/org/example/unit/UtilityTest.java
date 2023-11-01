package org.example.unit;

import org.example.src.utils.Utility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    Utility underTest = new Utility();

    @Test
    public void stripKeyWord_no_dashes_returns_name_with_no_prefix() {
        // Given
        String name = "gQk-these should all remain";

        // When
        String actual = underTest.stripKeyWord(name);

        // Then
        String expected = "these-should-all-remain";
        assertEquals(expected, actual);
    }

    @Test
    public void stripKeyWord_with_dashes_returns_name_with_no_prefix() {
        // Given
        String name = "gQk-these-should-all-remain";

        // When
        String actual = underTest.stripKeyWord(name);

        // Then
        String expected = "these-should-all-remain";
        assertEquals(expected, actual);
    }
}
