package com.servitware.id.exception;

public class Invalid_DNI_IdException extends Exception
{
	public Invalid_DNI_IdException() {
		super("Número de DNI no válido.");
	}
}
