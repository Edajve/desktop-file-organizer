package org.example.tests;

import org.example.src.operations.CommandLineParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineParserTest {

    CommandLineParser underTest = new CommandLineParser();

    @AfterEach
    public void afterEachTest() {
        underTest.clear();
    }

    @Test
    public void processArguments_pass_exit_as_argument_should_return_false() {
        // Given
        String[] argument = new String[]{"exit"};
        // When
        boolean result = underTest.processArguments(argument);
        // Then
        assertFalse(result);
    }

    @Test
    public void processArguments_pass_single_invalid_argument_should_return_false() {
        // Given
        String[] argument = new String[]{"invalid"};
        // When
        boolean result = underTest.processArguments(argument);
        // Then
        assertFalse(result);

    }

    @Test
    public void processArguments_pass_single_valid_argument_should_return_true() {
        // Given
        String[] argument = new String[]{"-delete"};
        // When
        boolean result = underTest.processArguments(argument);
        // Then
        assertTrue(result);
    }

    @Test
    public void processArguments_pass_multiple_arguments_contains_exit__should_return_true_and_delete_all_arguments_after_exit() {
        // Given
        String[] argument = new String[]{"argument1", "argument2", "exit", "argument3", "argument4"};
        // When
        boolean result = underTest.processArguments(argument);
        // Then
        List<String> actual = underTest.getArguments();
        String[] expected = new String[]{"argument1", "argument2", "exit"};

        assertTrue(result);
        assertEquals(new ArrayList<>(actual), new ArrayList<>(List.of(expected))); // converted types to ArrayList for easy validation
    }

    @Test
    public void processArguments_pass_multiple_invalid_argument_should_return_false() {
        // Given
        String[] argument = new String[]{"invalid1", "invalid2", "invalid3", "invalid4"};
        // When
        boolean result = underTest.processArguments(argument);
        // Then
        assertFalse(result);
    }

    @Test
    public void processArguments_pass_multiple_valid_argument_should_return_true_and_keep_all_arguments() {
        // Given
        String[] argument = new String[]{"-delete", "argument2", "argument3", "argument4", "argument5"};
        // When
        boolean result = underTest.processArguments(argument);
        // Then
        List<String> actual = List.of(new String[]{"-delete", "argument2", "argument3", "argument4", "argument5"});
        String[] expected = new String[]{"-delete", "argument2", "argument3", "argument4", "argument5"};

        assertTrue(result);
        assertEquals(new ArrayList<>(actual), new ArrayList<>(List.of(expected))); // converted types to ArrayList for easy validation
    }
}
