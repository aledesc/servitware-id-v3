package com.servitware.id;

import com.servitware.id.exception.Invalid_CIF_IdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CIFTest {

    // Test valid CIF/NIE numbers
    @ParameterizedTest
    @ValueSource(strings = {
            "X1234567A",  // Valid NIE starting with X
            "Y1234567B",  // Valid NIE starting with Y
            "Z1234567C",  // Valid NIE starting with Z
            "X12345678",  // Valid NIE with number as last character
            "X0000000T",  // Valid NIE with minimum number
            "X9999999R"   // Valid NIE with maximum number
    })
    public void testValidCIF(String cifNumber) throws Invalid_CIF_IdException {
        CIF cif = new CIF(cifNumber);
        assertEquals(cifNumber, cif.getId());
    }

    // Test invalid CIF structures
    @ParameterizedTest
    @ValueSource(strings = {
            "12345678A",  // Doesn't start with X/Y/Z
            "X123456A",   // Too short (7 characters)
            "X12345678A", // Too long (9 characters)
            "x1234567A",  // Lowercase initial letter
            "X-1234567A", // Contains hyphen
            "X1234567 ",  // Ends with space
            " X1234567A", // Starts with space
            "X1234567@",  // Invalid special character
            "XABCDEFGH",  // Letters in number part
            "M1234567A"   // Invalid initial letter
    })
    public void testInvalidStructureCIF(String cifNumber) {
        assertThrows(Invalid_CIF_IdException.class, () -> new CIF(cifNumber));
    }

    // Test null input
    @Test
    public void testNullCIF() {
        assertThrows(Invalid_CIF_IdException.class, () -> new CIF(null));
    }

    // Test empty string input
    @Test
    public void testEmptyCIF() {
        assertThrows(Invalid_CIF_IdException.class, () -> new CIF(""));
    }

    // Test case sensitivity for initial letter
    @Test
    public void testInitialLetterCaseSensitivity() {
        // Should fail because regex requires uppercase initial letter
        assertThrows(Invalid_CIF_IdException.class, () -> new CIF("x1234567A"));

        // These should pass
        assertDoesNotThrow(() -> new CIF("X1234567A"));
        assertDoesNotThrow(() -> new CIF("Y1234567B"));
        assertDoesNotThrow(() -> new CIF("Z1234567C"));
    }

    // Test all valid initial letters (X, Y, Z)
    @Test
    public void testAllValidInitialLetters() throws Invalid_CIF_IdException {
        CIF cifX = new CIF("X1234567A");
        assertEquals("X1234567A", cifX.getId());

        CIF cifY = new CIF("Y1234567B");
        assertEquals("Y1234567B", cifY.getId());

        CIF cifZ = new CIF("Z1234567C");
        assertEquals("Z1234567C", cifZ.getId());
    }

    // Test valid ending characters (both letters and numbers)
    @Test
    public void testValidEndingCharacters() throws Invalid_CIF_IdException {
        // Test with letter ending
        CIF letterEnding = new CIF("X1234567A");
        assertEquals("X1234567A", letterEnding.getId());

        // Test with number ending
        CIF numberEnding = new CIF("X12345678");
        assertEquals("X12345678", numberEnding.getId());
    }
}