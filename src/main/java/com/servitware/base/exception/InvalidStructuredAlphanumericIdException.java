package com.servitware.base.exception;

import com.servitware.base.StructuredStrId;

public class InvalidStructuredAlphanumericIdException extends Exception {
	public InvalidStructuredAlphanumericIdException()
	{
		super(StructuredStrId.INVALID_STRUCTURED_ID_EXCEPTION_MESSAGE);
	}
}
