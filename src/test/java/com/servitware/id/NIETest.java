package com.servitware.id;

import com.servitware.id.exception.Invalid_NIE_IdException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NIETest {

    @Test
    public void testValidNIEStartingWithX() {
        assertDoesNotThrow(() -> {
            NIE nie = new NIE("X12345678");
            assertEquals("X12345678", nie.getId());
        });
    }

    @Test
    public void testValidNIEStartingWithY() {
        assertDoesNotThrow(() -> {
            NIE nie = new NIE("Y87654321");
            assertEquals("Y87654321", nie.getId());
        });
    }

    @Test
    public void testValidNIEStartingWithZ() {
        assertDoesNotThrow(() -> {
            NIE nie = new NIE("Z00000000");
            assertEquals("Z00000000", nie.getId());
        });
    }

    @Test
    public void testValidNIEStartingWithW() {
        assertDoesNotThrow(() -> {
            NIE nie = new NIE("W99999999");
            assertEquals("W99999999", nie.getId());
        });
    }

    @Test
    public void testInvalidNIEWrongStartLetter() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("A12345678") );
    }

    @Test
    public void testInvalidNIENotEnoughDigits() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("X1234") );
    }

    @Test
    public void testInvalidNIEExtraCharacters() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("X123456789") );
    }

    @Test
    public void testInvalidNIEWithLettersInNumberPart() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE("X12A4567B"));
    }

    @Test
    public void testNullInput() {
        assertThrows(Invalid_NIE_IdException.class, () -> new NIE(null) );
    }
}
