package com.servitware.base;

/**
 * 
 * @author ALedesma
 */
public interface Named
{
	String REGEX_NAME= "^[a-zA-Z0-9_\\-\\.\\s]+$";
	String INVALID_NAME_EXCEPTION_MESSAGE = "Invalid name";

	String getName();
}
