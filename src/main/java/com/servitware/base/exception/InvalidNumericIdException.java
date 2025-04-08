package com.servitware.base.exception;

import com.servitware.base.IntId;

public class InvalidNumericIdException extends Exception
{
	public InvalidNumericIdException()
	{
		super(IntId.EXCEPTION_MESSAGE);
	}
}
