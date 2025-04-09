package com.servitware.base.exception;

import com.servitware.base.Named;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super(Named.INVALID_NAME_EXCEPTION_MESSAGE);
    }
}
