package com.servitware.base.impl;

import com.servitware.base.exception.InvalidAlphanumericIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class StrIdedTest {

    // Test valid constructions
    @ParameterizedTest
    @ValueSource(strings = {"a", "1", "abc123", "A1B2C3", " valid ", "  trimmed  ", "with-hyphen", "with_underscore"})
    void constructorWithValidString_ShouldCreateObject(String validId) {
        assertDoesNotThrow(() -> {
            StrIded strIded = new StrIded(validId);
            assertEquals(validId.trim(), strIded.getId());
        });
    }

    // Test invalid constructions (null, empty, blank)
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n", "\r\n"})
    void constructorWithNullOrBlankString_ShouldThrowException(String invalidId) {
        assertThrows(InvalidAlphanumericIdException.class, () -> new StrIded(invalidId));
    }

    // Test getId() method returns correct value
    @Test
    void getId_ShouldReturnCorrectValue() throws InvalidAlphanumericIdException {
        String expectedId = "test123";
        StrIded strIded = new StrIded(expectedId);
        assertEquals(expectedId.trim(), strIded.getId());
    }

    // Test whitespace trimming
    @ParameterizedTest
    @ValueSource(strings = {"  no-whitespace  ", "\ttabbed\t", "\nnewline\n"})
    void constructorShouldTrimWhitespace(String input) throws InvalidAlphanumericIdException {
        StrIded strIded = new StrIded(input);
        assertEquals(input.trim(), strIded.getId());
    }

    // Test edge cases
    @ParameterizedTest
    @ValueSource(strings = {"x", "X", "0", "9", "a1", "1a", "aaaaaaaaaaaaaaaaaaaaaaa"})
    void constructorWithEdgeCaseStrings_ShouldWork(String edgeCase) {
        assertDoesNotThrow(() -> {
            StrIded strIded = new StrIded(edgeCase);
            assertEquals(edgeCase.trim(), strIded.getId());
        });
    }

    // Test various valid string formats
    @ParameterizedTest
    @MethodSource("validStringIdsProvider")
    void constructorWithVariousValidStringFormats_ShouldWork(String validId) throws InvalidAlphanumericIdException {
        StrIded strIded = new StrIded(validId);
        assertEquals(validId.trim(), strIded.getId());
    }

    private static Stream<String> validStringIdsProvider() {
        return Stream.of(
                "simple",
                "123",
                "ABC123",
                "a1b2c3",
                "with-hyphen",
                "with_underscore",
                "with.dots",
                "mixed-Case",
                "UPPERCASE",
                "lowercase",
                "  leading-trailing  ",
                "\twhitespace\t",
                "a",  // single character
                "1",  // single digit
                "a".repeat(1000)  // long string
        );
    }

    // Test that the same instance returns the same ID
    @Test
    void getId_ShouldBeConsistent() throws InvalidAlphanumericIdException {
        StrIded strIded = new StrIded("consistent");
        assertEquals("consistent", strIded.getId());
        assertEquals(strIded.getId(), strIded.getId()); // multiple calls should return same value
    }

    // Test that constructor doesn't modify the string beyond trimming
    @Test
    void constructorShouldNotModifyStringBeyondTrimming() throws InvalidAlphanumericIdException {
        String original = "  original-content  ";
        StrIded strIded = new StrIded(original);
        assertEquals("original-content", strIded.getId());
        assertNotEquals(original, strIded.getId()); // because of trimming
    }
}