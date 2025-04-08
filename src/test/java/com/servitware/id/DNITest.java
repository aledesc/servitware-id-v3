package com.servitware.id;

import com.servitware.id.exception.Invalid_DNI_IdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class DNITest {

    // Test valid DNI numbers
    @ParameterizedTest
    @ValueSource(strings = {
            "12345678Z",  // Valid DNI
            "87654321X",  // Valid DNI
            "00000000T",  // Valid DNI (minimum value)
            "99999999R"   // Valid DNI (maximum value)
    })
    public void testValidDNI(String dniNumber) throws Invalid_DNI_IdException {
        DNI dni = new DNI(dniNumber);
        assertEquals(dniNumber, dni.getId());
    }

    // Test invalid DNI structures
    @ParameterizedTest
    @ValueSource(strings = {
            "1234567Z",   // Too short
            "123456789Z", // Too long
            "A2345678Z",  // Letter in number part
            "12345678",   // Missing letter
            "12345678@",  // Invalid character
            " 12345678Z", // Leading space
            "12345678Z "  // Trailing space
    })
    public void testInvalidStructureDNI(String dniNumber) {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(dniNumber));
    }

    // Test DNI with invalid control letter
    @ParameterizedTest
    @ValueSource(strings = {
            "12345678A",  // Invalid letter (should be Z)
            "87654321A",  // Invalid letter (should be X)
            "00000000A",  // Invalid letter (should be T)
            "99999999A"   // Invalid letter (should be R)
    })
    public void testInvalidControlLetterDNI(String dniNumber) {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(dniNumber));
    }

    // Test null input
    @Test
    public void testNullDNI() {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(null));
    }

    // Test empty string input
    @Test
    public void testEmptyDNI() {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(""));
    }

    // Test case sensitivity (should be case insensitive)
    @Test
    public void testCaseInsensitivity() throws Invalid_DNI_IdException {
        DNI lowerCase = new DNI("12345678z");
        DNI upperCase = new DNI("12345678Z");
        assertEquals(lowerCase.getId(), upperCase.getId());
    }

    // Test specific known valid DNI numbers
    @Test
    public void testKnownValidDNIs() throws Invalid_DNI_IdException {
        // 12345678 mod 23 = 14 → Z
        DNI dni1 = new DNI("12345678Z");
        assertEquals("12345678Z", dni1.getId());

        // 98765432 mod 23 = 10 → X
        DNI dni2 = new DNI("98765432X");
        assertEquals("98765432X", dni2.getId());
    }

    // Test all possible control letters
    @Test
    public void testAllControlLetters() throws Invalid_DNI_IdException {
        String[] validLetters = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X",
                "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        for (int i = 0; i < 23; i++) {
            String numberPart = String.format("%08d", i * 23); // Numbers that mod 23 = 0-22
            String dniNumber = numberPart + validLetters[i];
            DNI dni = new DNI(dniNumber);
            assertEquals(dniNumber, dni.getId());
        }
    }
}