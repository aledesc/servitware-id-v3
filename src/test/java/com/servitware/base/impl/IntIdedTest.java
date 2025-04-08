package com.servitware.base.impl;

import com.servitware.base.exception.InvalidNumericIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


// some concretion
class TestIntIded extends IntIded {
    public TestIntIded(int id) throws InvalidNumericIdException {
        super(id);
    }

    public TestIntIded(String id) throws InvalidNumericIdException {
        super(id);
    }
}

class IntIdedTest {
    private static final int VALID_ID = 1;
    private static final int MIN_ID = 0;

    @Test
    void constructorWithValidInt_shouldSetId() throws InvalidNumericIdException {
        IntIded obj = new TestIntIded(VALID_ID);
        assertEquals(VALID_ID, obj.getId());
    }

    @Test
    void constructorWithIntBelowMinId_shouldThrowException() {
        assertThrows(InvalidNumericIdException.class, () -> new TestIntIded(MIN_ID - 1));
    }

    @Test
    void constructorWithValidString_shouldSetId() throws InvalidNumericIdException {
        IntIded obj = new TestIntIded("123");
        assertEquals(123, obj.getId());
    }

    @Test
    void constructorWithBlankString_shouldThrowException() {
        assertThrows(InvalidNumericIdException.class, () -> new TestIntIded("   "));
    }

    @Test
    void constructorWithNullString_shouldThrowException() {
        assertThrows(InvalidNumericIdException.class, () -> new TestIntIded(null));
    }


    @Test
    void constructorWithEmptyString_shouldThrowException() {
        assertThrows(InvalidNumericIdException.class, () -> new TestIntIded(""));
    }

    @Test
    void constructorWithNonNumericString_shouldThrowException() {
        assertThrows(InvalidNumericIdException.class, () -> new TestIntIded("abc"));
    }

    @Test
    void constructorWithNegativeNumberString_shouldThrowException() {
        assertThrows(InvalidNumericIdException.class, () -> new TestIntIded("-123"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", " 123 ", "000123"})
    void constructorWithValidNumericFormats_shouldAccept(String input) throws InvalidNumericIdException {
        IntIded obj = new TestIntIded(input);
        assertEquals(Integer.parseInt(input.trim()), obj.getId());
    }
}
