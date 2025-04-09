package com.servitware.id;

import com.servitware.id.exception.Invalid_DNI_IdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class DNITest {

    // Valid DNIs with correct letter calculation
    private static final String[] VALID_DNIS = {
            "00000000T", "00000001R", "00000002W", "00000003A",
            "00000004G", "00000005M", "00000006Y", "00000007F",
            "00000008P", "00000009D", "00000010X", "00000011B",
            "00000012N", "00000013J", "00000014Z", "00000015S",
            "00000016Q", "00000017V", "00000018H", "00000019L",
            "00000020C", "00000021K", "00000022E", "12345678Z", "23456789D"
    };

    @Test
    void constructor_WithValidDNI_DoesNotThrowException() {
        for (String validDni : VALID_DNIS) {
            assertDoesNotThrow(() -> new DNI(validDni),
                    "Should not throw for valid DNI: " + validDni);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "00000000A", "00000001B", "00000002C", "00000003D",
            "12345678A", "98765432B", "45678901C", "23456789E"
    })
    void constructor_WithInvalidLetter_ThrowsInvalidDNIIdException(String invalidDni) {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(invalidDni),
                "Should throw for DNI with invalid letter: " + invalidDni);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234567A",   // Too short
            "123456789A",  // Too long
            "A2345678B",   // Letter at start
            "123-4567X",   // Contains hyphen
            "12345678 ",   // Trailing space
            " 12345678T",  // Leading space
            "12345678t",   // Lowercase letter
            "1234567X8",   // Letter in wrong position
            "12345678*"    // Invalid character
    })
    void constructor_WithInvalidFormat_ThrowsInvalidDNIIdException(String invalidDni) {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(invalidDni),
                "Should throw for DNI with invalid format: " + invalidDni);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_WithNullOrEmpty_ThrowsInvalidDNIIdException(String input) {
        assertThrows(Invalid_DNI_IdException.class, () -> new DNI(input));
    }

    @Test
    void getId_ReturnsCorrectValue() throws Invalid_DNI_IdException {
        String testDni = "12345678Z";
        DNI dni = new DNI(testDni);
        assertEquals(testDni, dni.getId());
    }

    @Test
    void getId_ReturnsSameValueAsInput() throws Invalid_DNI_IdException {
        for (String validDni : VALID_DNIS) {
            DNI dni = new DNI(validDni);
            assertEquals(validDni, dni.getId(),
                    "getId() should return the same value as input for DNI: " + validDni);
        }
    }

    @Test
    void differentObjectsWithSameDNI_AreIndependent() throws Invalid_DNI_IdException {
        String testDni = "00000013J";
        DNI dni1 = new DNI(testDni);
        DNI dni2 = new DNI(testDni);

        assertEquals(dni1.getId(), dni2.getId());
        assertNotSame(dni1, dni2);
    }


    private static Stream<String> provideValidDNILetterCombinations() {
        return Stream.of(VALID_DNIS);
    }

}