package com.servitware.base.impl;

import com.servitware.base.Named;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

class StrNamedTest {

    // Test valid constructions
    @ParameterizedTest
    @MethodSource("validIdAndNameProvider")
    void constructorWithValidParameters_ShouldCreateObject(String id, String name) {
        assertDoesNotThrow(() -> {
            StrNamed strNamed = new StrNamed(id, name);
            assertEquals(id.trim(), strNamed.getId());
            assertEquals(name, strNamed.getName());
        });
    }

    // Test invalid ID cases (inherited from StrIded)
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void constructorWithInvalidId_ShouldThrowInvalidAlphanumericIdException(String invalidId) {
        assertThrows(InvalidAlphanumericIdException.class,
                () -> new StrNamed(invalidId, "validName"));
    }

    // Test invalid name cases
    @ParameterizedTest
    @NullAndEmptySource
    void constructorWithInvalidName_ShouldThrowInvalidNameException(String invalidName) {
        assertThrows(InvalidNameException.class,
                () -> new StrNamed("validId", invalidName));
    }

    // Test getName() method
    @Test
    void getName_ShouldReturnCorrectValue() throws Exception {
        String expectedName = "Test Name";
        StrNamed strNamed = new StrNamed("id1", expectedName);
        assertEquals(expectedName, strNamed.getName());
    }

    // Test edge cases for name
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "A", "1", "Long name with spaces", "Special!@#$"})
    void constructorWithEdgeCaseNames_ShouldWork(String name) {
        assertDoesNotThrow(() -> {
            StrNamed strNamed = new StrNamed("validId", name);
            assertEquals(name, strNamed.getName());
        });
    }

    // Test main method (basic verification)
    @Test
    void main_ShouldHandleInvalidInput() {
        assertDoesNotThrow(() -> StrNamed.main(new String[]{}));
    }

    // Test inheritance
    @Test
    void shouldBeInstanceOfNamedAndStrIded() throws Exception {
        StrNamed strNamed = new StrNamed("id1", "name1");
        assertTrue(strNamed instanceof Named);
        assertTrue(strNamed instanceof StrIded);
    }

    private static Stream<Arguments> validIdAndNameProvider() {
        return Stream.of(
                arguments("id1", "Name 1"),
                arguments("ID2", "Name Two"),
                arguments("  id3  ", "Trimmed Name"),
                arguments("a", "Single Character Name"),
                arguments("long-id-123", "Long Name With Spaces And Special Chars!@#")
        );
    }

    // Test name with whitespace only (should be allowed if not empty)
    @ParameterizedTest
    @ValueSource(strings = {" ", "  name  ", "\tname\t", "\nname\n"})
    void constructorWithWhitespaceInName_ShouldWork(String name) {
        assertDoesNotThrow(() -> {
            StrNamed strNamed = new StrNamed("validId", name);
            assertEquals(name, strNamed.getName()); // Note: name is not trimmed
        });
    }

    // Test that empty name is rejected
    @Test
    void constructorWithEmptyName_ShouldThrowException() {
        assertThrows(InvalidNameException.class,
                () -> new StrNamed("validId", ""));
    }
}