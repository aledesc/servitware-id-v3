package com.servitware.base.exception;

import com.servitware.base.StrId;

public class InvalidStructuredAlphanumericIdException extends Exception
{
	public InvalidStructuredAlphanumericIdException()
	{
		super(StrId.INVALID_ALPHANUMERIC_EXCEPTION_MESSAGE);
	}
}
