package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyTest {
    @Test
    public void testAdd() {
        // Given
        Dummy underTest = new Dummy();

        // When
        assertEquals(13, underTest.addNums(7, 6));
    }

    @Test
    public void subtractAdd() {
        // Given
        Dummy underTest = new Dummy();

        // When
        assertEquals(1, underTest.subtractNum(7, 6));
    }
}
