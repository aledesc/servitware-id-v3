package com.servitware.id.exception;

import com.servitware.base.StrId;

public class Invalid_NIE_IdException extends Exception
{
	public Invalid_NIE_IdException() {
		super("Número de NIE no válido.");
	}
}
