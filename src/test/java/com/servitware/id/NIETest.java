package com.servitware.id;

import com.servitware.id.exception.Invalid_NIE_IdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class NIETest {

    // Test valid NIE numbers
    @ParameterizedTest
    @ValueSource(strings = {
            "X12345678",  // Valid NIE starting with X
            "Y00000000",  // Valid NIE starting with Y (minimum)
            "Z99999999",  // Valid NIE starting with Z (maximum)
            "X98765432",  // Valid NIE random number
            "Y12345678"   // Valid NIE starting with Y
    })
    public void testValidNIE(String nieNumber) throws Invalid_NIE_IdException {
        NIE nie = new NIE(nieNumber);
        assertEquals(nieNumber, nie.getId());
    }

    // Test invalid NIE structures
    @ParameterizedTest
    @ValueSource(strings = {
            "123456789",   // Doesn't start with X/Y/Z
            "X1234567",    // Too short (8 characters required)
            "X123456789",  // Too long
            "x12345678",   // Lowercase initial letter
            "X-12345678",  // Contains hyphen
            "X12345678 ",  // Ends with space
            " X12345678",  // Starts with space
            "X1234567A",   // Ends with letter
            "XABCDEFGH",   // Letters in number part
            "M12345678",   // Invalid initial letter
            "X1234567.",    // Contains special character
            "X1234567_8"    // Contains underscore
    })
    public void testInvalidStructureNIE(String nieNumber) {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE(nieNumber));
    }

    // Test null input
    @Test
    public void testNullNIE() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE(null));
    }

    // Test empty string input
    @Test
    public void testEmptyNIE() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE(""));
    }

    // Test case sensitivity for initial letter
    @Test
    public void testInitialLetterCaseSensitivity() {
        // Should fail because regex requires uppercase initial letter
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("x12345678"));

        // These should pass
        assertDoesNotThrow(() -> new NIE("X12345678"));
        assertDoesNotThrow(() -> new NIE("Y12345678"));
        assertDoesNotThrow(() -> new NIE("Z12345678"));
    }

    // Test all valid initial letters (X, Y, Z)
    @Test
    public void testAllValidInitialLetters() throws Invalid_NIE_IdException {
        NIE nieX = new NIE("X00000000");
        assertEquals("X00000000", nieX.getId());

        NIE nieY = new NIE("Y00000000");
        assertEquals("Y00000000", nieY.getId());

        NIE nieZ = new NIE("Z00000000");
        assertEquals("Z00000000", nieZ.getId());
    }

    // Test boundary values for numeric part
    @Test
    public void testBoundaryValues() throws Invalid_NIE_IdException {
        // Minimum value
        NIE min = new NIE("X00000000");
        assertEquals("X00000000", min.getId());

        // Maximum value
        NIE max = new NIE("X99999999");
        assertEquals("X99999999", max.getId());
    }

    // Test that only digits are allowed after initial letter
    @Test
    public void testNumericPartValidation() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("X1234A678"));
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("X1234567 "));
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("X1234567."));
    }
}