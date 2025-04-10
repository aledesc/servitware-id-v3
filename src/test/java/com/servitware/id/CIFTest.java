package com.servitware.id;

import com.servitware.id.exception.Invalid_CIF_IdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class CIFTest {

    // Valid CIFs according to the regex pattern
    private static final String[] VALID_CIFS = {
            "A12345678", "B98765432", "C00123456", "D55555555",
            "E1234567A", "F9876543Z", "G0012345X", "H5555555Y",
            "K1234567B", "L9876543C", "M0012345D", "N5555555E",
            "P1234567F", "Q9876543G", "S0012345H"
    };

    @Test
    void constructor_WithValidCIF_DoesNotThrowException() {
        for (String validCif : VALID_CIFS) {
            assertDoesNotThrow(() -> new CIF(validCif),
                    "Should not throw for valid CIF: " + validCif);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Z12345678", // Invalid first letter
            "A1234567",   // Too short
            "A123456789", // Too long
            "A12-45678",  // Contains invalid character
            "A1234567 ",   // Trailing space
            " A12345678", // Leading space
            "a12345678",   // Lowercase first letter
            "I12345678",   // Invalid first letter (I)
            "J12345678",   // Invalid first letter (J)
            "O12345678",   // Invalid first letter (O)
            "R12345678",   // Invalid first letter (R)
            "T12345678",   // Invalid first letter (T)
            "U12345678",   // Invalid first letter (U-Z except those allowed)
            "V12345678",
            "W12345678",
            "X12345678",
            "Y12345678",
            "Z12345678",
            "A12X45678",   // Non-digit in the middle
            "A1234567*"    // Invalid last character
    })
    void constructor_WithInvalidCIF_ThrowsInvalidCIFIdException(String invalidCif) {
        assertThrows(Invalid_CIF_IdException.class, () -> new CIF(invalidCif),
                "Should throw for invalid CIF: " + invalidCif);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructor_WithNullOrEmpty_ThrowsInvalidCIFIdException(String input) {
        assertThrows(Invalid_CIF_IdException.class, () -> new CIF(input));
    }

    @Test
    void getId_ReturnsCorrectValue() throws Invalid_CIF_IdException {
        String testCif = "A12345678";
        CIF cif = new CIF(testCif);
        assertEquals(testCif, cif.getId());
    }

    @Test
    void getId_ReturnsSameValueAsInput() throws Invalid_CIF_IdException {
        for (String validCif : VALID_CIFS) {
            CIF cif = new CIF(validCif);
            assertEquals(validCif, cif.getId(),
                    "getId() should return the same value as input for CIF: " + validCif);
        }
    }

    @Test
    void differentObjectsWithSameCIF_AreIndependent() throws Invalid_CIF_IdException {
        String testCif = "B98765432";
        CIF cif1 = new CIF(testCif);
        CIF cif2 = new CIF(testCif);

        assertEquals(cif1.getId(), cif2.getId());
        assertNotSame(cif1, cif2);
    }

}