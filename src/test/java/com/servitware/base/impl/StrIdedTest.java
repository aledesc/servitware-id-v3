package com.servitware.base.impl;

import com.servitware.base.exception.InvalidAlphanumericIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.*;

// some concretion
class TestStrIded extends StrIded {
    public TestStrIded(String id) throws InvalidAlphanumericIdException {
        super(id);
    }
}

public class StrIdedTest {

    @Test
    @DisplayName("Constructor with valid ID should set ID correctly")
    void constructorWithValidId_shouldSetId() throws Exception {
        String validId = "valid123";
        StrIded strIded = new TestStrIded(validId);
        assertEquals(validId, strIded.getId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    @DisplayName("Constructor should throw InvalidAlphanumericIdException for null/blank IDs")
    void constructorWithNullOrBlankId_shouldThrowException(String invalidId) {
        assertThrows(InvalidAlphanumericIdException.class,
                () -> new TestStrIded(invalidId));
    }

    @Test
    @DisplayName("getId() should return the initialized ID")
    void getId_shouldReturnInitializedId() throws Exception {
        String expectedId = "test-id_123";
        StrIded strIded = new TestStrIded(expectedId);
        assertEquals(expectedId, strIded.getId());
    }

    @Test
    @DisplayName("Two instances with same ID should be equal if equals() is implemented")
    void instancesWithSameId_shouldBeEqual() throws Exception {
        String id = "same-id";
        StrIded instance1 = new TestStrIded(id);
        StrIded instance2 = new TestStrIded(id);

        // This assumes equals() is implemented in StrIded
        assertEquals(instance1, instance2);
    }

    @Test
    @DisplayName("ID should be immutable after construction")
    void idShouldBeImmutable() throws Exception {
        String originalId = "original";
        StrIded strIded = new TestStrIded(originalId);

        // Try to modify the ID through reflection (test immutability)
        try {
            var field = StrIded.class.getDeclaredField("id");
            field.setAccessible(true);
            assertThrows(IllegalAccessException.class,
                    () -> field.set(strIded, "modified"));
        } catch (NoSuchFieldException e) {
            fail("Field 'id' not found in StrIded class");
        }
    }
}