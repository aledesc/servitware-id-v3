package com.servitware.id.exception;

public class Invalid_CIF_IdException extends Exception
{
	public Invalid_CIF_IdException() {
		super("Número de CIF no válido.");
	}
}
