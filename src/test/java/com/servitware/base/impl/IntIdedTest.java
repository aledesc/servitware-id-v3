package com.servitware.base.impl;

import com.servitware.base.IntId;
import com.servitware.base.exception.InvalidNumericIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class IntIdedTest {

    // Test valid constructions with int constructor
    @ParameterizedTest
    @ValueSource(ints = {IntId.MIN_ID, IntId.MIN_ID + 1, 100, Integer.MAX_VALUE})
    void constructorWithValidInt_ShouldCreateObject(int validId) {
        assertDoesNotThrow(() -> {
            IntIded intIded = new IntIded(validId);
            assertEquals(validId, intIded.getId());
        });
    }

    // Test invalid constructions with int constructor
    @ParameterizedTest
    @ValueSource(ints = {IntId.MIN_ID - 1, -1, Integer.MIN_VALUE})
    void constructorWithInvalidInt_ShouldThrowException(int invalidId) {
        assertThrows(InvalidNumericIdException.class, () -> new IntIded(invalidId));
    }

    // Test valid constructions with String constructor
    @ParameterizedTest
    @ValueSource(strings = {"1", " 123 ", "999999999", "" + Integer.MAX_VALUE})
    void constructorWithValidString_ShouldCreateObject(String validId) {
        assertDoesNotThrow(() -> {
            IntIded intIded = new IntIded(validId);
            assertEquals(Integer.parseInt(validId.trim()), intIded.getId());
        });
    }

    // Test invalid constructions with String constructor (null, empty, blank)
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void constructorWithNullOrBlankString_ShouldThrowException(String invalidId) {
        assertThrows(InvalidNumericIdException.class, () -> new IntIded(invalidId));
    }

    // Test invalid string formats
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "1.0", "abc", "123abc", "1,000"})
    void constructorWithInvalidStringFormat_ShouldThrowException(String invalidId) {
        assertThrows(InvalidNumericIdException.class, () -> new IntIded(invalidId));
    }

    // Test string representations of invalid numeric values
    @ParameterizedTest
    @ValueSource(strings = {"" + (IntId.MIN_ID - 1), "" + Integer.MIN_VALUE})
    void constructorWithStringRepresentingInvalidInt_ShouldThrowException(String invalidId) {
        assertThrows(InvalidNumericIdException.class, () -> new IntIded(invalidId));
    }

    // Test getId() method returns correct value
    @Test
    void getId_ShouldReturnCorrectValue() throws InvalidNumericIdException {
        int expectedId = 42;
        IntIded intIded = new IntIded(expectedId);
        assertEquals(expectedId, intIded.getId());
    }

    // Test edge case with MIN_ID
    @Test
    void constructorWithMinId_ShouldWork() throws InvalidNumericIdException {
        IntIded intIded = new IntIded(IntId.MIN_ID);
        assertEquals(IntId.MIN_ID, intIded.getId());
    }

    // Test that constructor throws for numeric strings that are too large
    @Test
    void constructorWithTooLargeNumber_ShouldThrowNumberFormatException() {
        String tooLarge = "99999999999999999999";
        assertThrows(NumberFormatException.class, () -> new IntIded(tooLarge));
    }

    // Additional test cases for string constructor
    @ParameterizedTest
    @MethodSource("validStringIdsProvider")
    void constructorWithVariousValidStringFormats_ShouldWork(String validId) throws InvalidNumericIdException {
        IntIded intIded = new IntIded(validId);
        assertEquals(Integer.parseInt(validId.trim()), intIded.getId());
    }

    private static Stream<String> validStringIdsProvider() {
        return Stream.of(
                "1",
                " 2 ",
                "  3  ",
                "123456789",
                "" + IntId.MIN_ID
        );
    }
}