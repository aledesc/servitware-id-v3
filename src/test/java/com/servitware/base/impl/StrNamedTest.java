package com.servitware.base.impl;

import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

// the concrete class
class TestStrNamed extends StrNamed {
    public TestStrNamed(String id, String name) throws InvalidAlphanumericIdException, InvalidNameException {
        super(id, name);
    }
}

class StrNamedTest {

    private static final String VALID_ID = "test123";
    private static final String VALID_NAME = "Test Name";

    @Test
    @DisplayName("Constructor with valid id and name should initialize successfully")
    void constructorWithValidParameters_shouldInitialize() throws Exception {
        StrNamed named = new TestStrNamed(VALID_ID, VALID_NAME);
        assertAll(
                () -> assertEquals(VALID_ID, named.getId()),
                () -> assertEquals(VALID_NAME, named.getName())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Constructor should throw InvalidNameException for null/empty names")
    void constructorWithInvalidName_shouldThrowException(String invalidName) {
        assertThrows(InvalidNameException.class,
                () -> new TestStrNamed(VALID_ID, invalidName));
    }

    @Test
    @DisplayName("Constructor should propagate InvalidAlphanumericIdException from parent")
    void constructorWithInvalidId_shouldPropagateException() {
        assertThrows(InvalidAlphanumericIdException.class,
                () -> new TestStrNamed("", VALID_NAME));
    }

    @Test
    @DisplayName("getName() should return the initialized name")
    void getName_shouldReturnInitializedName() throws Exception {
        StrNamed named = new TestStrNamed(VALID_ID, VALID_NAME);
        assertEquals(VALID_NAME, named.getName());
    }

    @Test
    @DisplayName("Name field should be immutable after construction")
    void nameShouldBeImmutable() throws Exception {
        StrNamed named = new TestStrNamed(VALID_ID, VALID_NAME);

        try {
            var field = StrNamed.class.getDeclaredField("name");
            field.setAccessible(true);
            assertThrows(IllegalAccessException.class,
                    () -> field.set(named, "modified"));
        } catch (NoSuchFieldException e) {
            fail("Field 'name' not found in StrNamed class");
        }
    }
}
