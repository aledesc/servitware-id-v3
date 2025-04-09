package com.servitware.base.exception;

import com.servitware.base.StrId;

public class InvalidAlphanumericIdException extends Exception {
	public InvalidAlphanumericIdException() {
		super(StrId.INVALID_ALPHANUMERIC_ID_EXCEPTION_MESSAGE);
	}
}
