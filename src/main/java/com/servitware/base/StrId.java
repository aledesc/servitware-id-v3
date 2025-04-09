package com.servitware.base;

/**
 * @author ALedesma
 *
 */
public interface StrId
{
	String REGEX_ALPHA_NUMERIC_ID= "^[a-zA-Z0-9_\\-\\.\\s]+$";
	String INVALID_ALPHANUMERIC_ID_EXCEPTION_MESSAGE = """
            Invalid alphanumeric Id, can not be null nor empty, can just include: letters (a-z,A-Z), numbers (0-9),\s
            dash (-), underscore (_), space ( ), and dot (.)""";

	String getId();
}
